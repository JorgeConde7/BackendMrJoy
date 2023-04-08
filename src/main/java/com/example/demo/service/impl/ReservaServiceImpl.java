package com.example.demo.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.DataResponseDTO;
import com.example.demo.exception.ErrorException;
import com.example.demo.exception.MrJoyException;
import com.example.demo.model.Cliente;
import com.example.demo.model.Empleado;
import com.example.demo.model.Login;
import com.example.demo.model.Reserva;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.EmpleadoRespository;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.service.ReservaService;
import com.example.demo.util.Constantes;
import com.example.demo.util.Utilitarios;

import ch.qos.logback.classic.Logger;

@Service
public class ReservaServiceImpl implements ReservaService {
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EmpleadoRespository empleadoRespository;
	
	
	@Override
	@Transactional(readOnly=true)
	public List<Reserva> findAll() {
		return(List<Reserva>) reservaRepository.findAll();
	}

	@Override
	public Reserva findById(Long id) {
		return reservaRepository.findById(id).orElse(null);
	}

	@Override
	public Reserva guardarReserva(Reserva reserva) throws Exception {
		try {
			if(reserva.getCantPersonas()<15){
				throw new MrJoyException("COD02","Las reservas son sólo apartir de 15 personas en adelante");
			}
			formaterFecha(reserva);
			
			Login login= new Login();
			long id=reserva.getIdLogin();
			Optional<Login> datosLogin=loginRepository.findById(id);
			login=datosLogin.get();
			
			if(login.getTipouser().equals(Constantes.VALOR_CLIENTE)) {
				reserva.setFlagTipoReserva(Constantes.FLAG_CLIENTE);
			}
			if(login.getTipouser().equals(Constantes.VALOR_EMPLEADO) || 
					login.getTipouser().equals(Constantes.VALOR_ADMIN)) {
				reserva.setFlagTipoReserva(Constantes.FlAG_EMPLEADO);	
			}		
			
			Utilitarios utilitarios= new Utilitarios();
			if(reserva.getFechaRegistro()==null) {
				reserva.setFechaRegistro(utilitarios.ObtenerFechaActual());
				reserva.setEstado(Constantes.ESTADO_RESERVA_VIGENTE);
				reserva.setDiferenciaPagar(0);
			}
			reserva.setHasEmail(false);
		
			return reservaRepository.save(reserva);
			
		}catch(MrJoyException e) {
			throw e;
		}catch(Exception e) {
			throw new ErrorException();
		}
		
	}

	
	
	@Override
	public List<Reserva> ListarPorFecha(Date fechaReserva) {
		List<Reserva> reserva= new ArrayList<>();
		reserva=reservaRepository.findByfechaReservaAndEstado(fechaReserva,Constantes.ESTADO_RESERVA_VIGENTE);	
		return reserva;
		
	}

	@Override
	public DataResponseDTO<String> eliminarReserva(Reserva reserva, Long idReserva) throws Exception{
		Utilitarios utilitarios= new Utilitarios();

		try {
			Reserva reservaActual=reservaRepository.findById(idReserva).orElse(null);
			
			if(!reservaActual.getEstado().equals(Constantes.ESTADO_RESERVA_VIGENTE)) {
				throw new MrJoyException("COD07","No se puede eliminar una reserva caducada o anulada");
			}
			
			DataResponseDTO<String> response = new DataResponseDTO<>();
			
				Login login=loginRepository.findById((long) reserva.getIdLogin()).orElse(null);
				
				reservaActual.setFechaModificacion(utilitarios.ObtenerFechaActual());
				int difDias=CalcularDiferenciaDias(reservaActual);
				
				if((login.getTipouser().equals(Constantes.VALOR_ADMIN))){
					
					reservaActual.setEstado(Constantes.ESTADO_RESERVA_ANULADO);
					reservaActual.setMotivoAnulacion(reserva.getMotivoAnulacion());
					setUsuarioModificacion(reserva, reservaActual);
					reservaRepository.save(reservaActual);
					
					response.setStatus(200);
					response.setMessage("Se anulo la reserva correctamente. Se realizará la devolucion del dinero en un plazo de 2 dias habiles.");
						
					return response;
				}
				
				if ( (login.getTipouser().equals(Constantes.VALOR_CLIENTE) || 
						login.getTipouser().equals(Constantes.VALOR_EMPLEADO)) && difDias <= 2) {
					
					reservaActual.setEstado(Constantes.ESTADO_RESERVA_ANULADO);
					reservaActual.setMotivoAnulacion(reserva.getMotivoAnulacion());
					setUsuarioModificacion(reserva, reservaActual);
					reservaRepository.save(reservaActual);
					response.setStatus(200);
					response.setMessage("Se anulo la reserva correctamente. Se realizará la devolucion del dinero en un plazo de 2 dias habiles.");
						
					return response;
				}else {
					throw new MrJoyException("COD03","La reserva no se puede anular");
				}
								
			
		}catch(MrJoyException e) {
			throw e;
		}catch(Exception e) {
			throw new ErrorException();
		}
		
	}

	private void setUsuarioModificacion(Reserva reserva, Reserva reservaActual) {

		if(reserva.getFlagTipoReserva().equals(Constantes.FLAG_CLIENTE)) {
			Cliente	cliente=clienteRepository.findByIdLogin((long) reserva.getIdLogin());
			reservaActual.setUsuarioModificacion(cliente.getNombres()+" "+cliente.getApePaterno());
		}
		
		if(reserva.getFlagTipoReserva().equals(Constantes.FlAG_EMPLEADO)) {
			Empleado empleado= empleadoRespository.findByIdLogin((long) reserva.getIdLogin());
			reservaActual.setUsuarioModificacion(empleado.getNombres()+" "+empleado.getApellidos());
		}
	}

	private int CalcularDiferenciaDias(Reserva reservaActual) {
		Calendar calInicio = Calendar.getInstance();
		Calendar calFin = Calendar.getInstance();
		calInicio.setTime(reservaActual.getFechaRegistro());
		calFin.setTime(reservaActual.getFechaModificacion());
		
		long difMillis = calFin.getTimeInMillis() - calInicio.getTimeInMillis();
		int difDias = (int) (difMillis / (1000 * 60 * 60 * 24));
		return difDias;
	}
	

	
	@Override
	public List<Reserva> listarPorIdLogin(int idLogin) {
		return (List<Reserva>) reservaRepository.findByIdLoginOrderByEstadoDesc(idLogin);
	}

	@Override
	public List<Reserva> buscarReserva(String campo, String valor) throws Exception {
		
		switch(campo) {
	      case "nombres":
	        return reservaRepository.findByNombres(valor);
	      case "apellido":
	        return reservaRepository.findByApellido(valor);
	      case "dni":
	        return reservaRepository.findByDni(valor);
	      default:
	        return new ArrayList<Reserva>();
	    }
	 }
	
	
	
	private void formaterFecha(Reserva reserva) {
		Date fechaReserva = reserva.getFechaReserva();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = formatter.format(fechaReserva);
		reserva.setFechaReserva(Date.valueOf(formattedDate));
	}

	
	

}
