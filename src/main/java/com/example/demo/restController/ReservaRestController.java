package com.example.demo.restController;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.example.demo.service.ReservaService;
import com.example.demo.util.Constantes;
import com.example.demo.util.Utilitarios;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/apireserva")
public class ReservaRestController {
	
	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EmpleadoRespository empleadoRespository;
	
	@Autowired
	private LoginRepository loginRepository;

	@GetMapping("/reservas")
	public List<Reserva> index() {
		return reservaService.findAll();
	}

	@GetMapping("/reservas/{id}")
	public Reserva show(@PathVariable Long id) {
		return reservaService.findById(id);
	}

	@PostMapping("/reservas")
	public ResponseEntity<?> guardarReserva(@RequestBody Reserva reserva) throws Exception {
		try{
			Reserva newReserva = reservaService.guardarReserva(reserva);
            return ResponseEntity.status(HttpStatus.OK).body(newReserva);
        }catch (DataAccessException e){
            return  (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

	// actualizar
	@PutMapping("/reservas/{id}")
	public DataResponseDTO<String> actualizarReserva(@RequestBody Reserva reserva, @PathVariable Long id) throws Exception {
		DataResponseDTO<String> response = new DataResponseDTO<>();
		try {
			Reserva reservaActual = reservaService.findById(id);										
			Utilitarios utilitarios= new Utilitarios();		
		
			if(reservaActual.getEstado().equals(Constantes.ESTADO_RESERVA_VIGENTE)) {
				
				Login datosLogin=loginRepository.findById((long) reserva.getIdLogin()).orElse(null);
		
				reservaActual.setFechaModificacion(utilitarios.ObtenerFechaActual());
				/*reservaActual.setHasEmail(false);*/
				int difDias = CalcularDiferenciaDias(reservaActual);	
		
				if( (datosLogin.getTipouser().equals(Constantes.VALOR_ADMIN))) {
					
					reservaActual.setFechaReserva(reserva.getFechaReserva());
					reservaActual.setHora(reserva.getHora());
					reservaActual.setEmail(reserva.getEmail());
					reservaActual.setCantPersonas(reserva.getCantPersonas());
					reservaActual.setTelefono(reserva.getTelefono());
					reservaActual.setIdPaquete(reserva.getIdPaquete());
					reservaActual.setAcompaniante(reserva.getAcompaniante());
					double diferencia= reserva.getTotalPago()-reservaActual.getTotalPago();
					reservaActual.setTotalPago(reserva.getTotalPago());
					
					reservaActual.setDiferenciaPagar(diferencia);	
					DiferenciarUsuarioModificacion(reserva,reservaActual);
					reservaService.guardarReserva(reservaActual);
					response.setStatus(200);
					response.setMessage("Se actualizo la reserva correctamente \n Diferencia a pagar/devolver :"+diferencia);
					
				    return response;
					
				}
				
				if( (datosLogin.getTipouser().equals(Constantes.VALOR_CLIENTE) || 
						datosLogin.getTipouser().equals(Constantes.VALOR_EMPLEADO)) && difDias > 6) {
					
					reservaActual.setFechaReserva(reserva.getFechaReserva());
					reservaActual.setHora(reserva.getHora());
					reservaActual.setEmail(reserva.getEmail());
					reservaActual.setCantPersonas(reserva.getCantPersonas());
					reservaActual.setTelefono(reserva.getTelefono());
					reservaActual.setIdPaquete(reserva.getIdPaquete());
					reservaActual.setAcompaniante(reserva.getAcompaniante());
					double diferencia= reserva.getTotalPago()-reservaActual.getTotalPago();
					reservaActual.setTotalPago(reserva.getTotalPago());
					
					reservaActual.setDiferenciaPagar(diferencia);	
					DiferenciarUsuarioModificacion(reserva,reservaActual);
					reservaService.guardarReserva(reservaActual);
					response.setStatus(200);
					response.setMessage("Se actualizo la reserva correctamente \n Diferencia a pagar/devolver :"+diferencia);
					
				    return response;
					
				}else {
					throw new MrJoyException("COD03","Estimado cliente, su reserva se no se puede modificar ya que esta a menos de 1 semana de empezar");
				}			
			}
			else {
				throw new MrJoyException("COD04","Estimado Cliente, no se puede actualizar una reserva caducada o anulada");
			}
			
		}catch(MrJoyException e) {
			throw e;
		}catch (Exception e) {
			throw new ErrorException();
		}
		
	}

	
	private void DiferenciarUsuarioModificacion(Reserva reserva,Reserva reservaActual) {

		if(reserva.getFlagTipoReserva().equals(Constantes.FLAG_CLIENTE)) {
			Cliente	cliente=clienteRepository.findByIdLogin((long) reserva.getIdLogin());
			reservaActual.setUsuarioModificacion(cliente.getNombres()+" "+cliente.getApePaterno());
		}
		
		if(reserva.getFlagTipoReserva().equals(Constantes.FlAG_EMPLEADO)) {
			Empleado empleado= empleadoRespository.findByIdLogin((long) reserva.getIdLogin());
			reservaActual.setUsuarioModificacion(empleado.getNombres()+" "+empleado.getApellidos());
		}
	}

	@PostMapping("/anularReserva/{id}")
	public DataResponseDTO<String>  eliminarReserva(@RequestBody Reserva reserva,@PathVariable Long id) throws Exception{
		return reservaService.eliminarReserva(reserva, id);
	}
		
	@GetMapping("/reservas-fecha/{fecha}")
	public List<Reserva> listarPorFecha(@PathVariable Date fecha) {
		return reservaService.ListarPorFecha(fecha);
	}
	
	@GetMapping("/reservas-idLogin/{idLogin}")
	public List<Reserva> listarPorIdLogin(@PathVariable int idLogin) {
		return reservaService.listarPorIdLogin(idLogin);
	}
	
	 @GetMapping("/buscar")
	  public List<Reserva> buscar(@RequestParam("campo") String campo, @RequestParam("valor") String valor) throws Exception {
	    return reservaService.buscarReserva(campo, valor);
	  }
	
	private int CalcularDiferenciaDias(Reserva reservaActual) {
		Calendar calInicio = Calendar.getInstance();
		Calendar calFin = Calendar.getInstance();
		calInicio.setTime(reservaActual.getFechaModificacion());
		calFin.setTime(reservaActual.getFechaReserva());
		
		long difMillis = calFin.getTimeInMillis() - calInicio.getTimeInMillis();
		int difDias = (int) (difMillis / (1000 * 60 * 60 * 24));
		return difDias;
	}
	
	
	
}
