package com.example.demo.restController;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TotalVentasDTO;
import com.example.demo.exception.ErrorException;
import com.example.demo.exception.MrJoyException;
import com.example.demo.model.Cliente;
import com.example.demo.model.Empleado;
import com.example.demo.model.Login;
import com.example.demo.model.Paquetes;
import com.example.demo.model.Reserva;
import com.example.demo.model.response.DataResponse;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.EmpleadoRespository;
import com.example.demo.repository.LoginRepository;
import com.example.demo.service.PaqueteService;
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
	private PaqueteService paqueteService;
	
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
		DataResponse<String> response = new DataResponse<>();
		try{
            return ResponseEntity.status(HttpStatus.OK).body(reservaService.guardarReserva(reserva));
        }catch (DataAccessException e){
            return  (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

	// actualizar
	@PutMapping("/reservas/{id}")
	public Reserva update(@RequestBody Reserva reserva, @PathVariable Long id) throws Exception {
		try {
			Reserva reservaActual = reservaService.findById(id);										
			Utilitarios utilitarios= new Utilitarios();
			Login login= new Login();			
		
			if(reservaActual.getEstado().equals(Constantes.ESTADO_RESERVA_VIGENTE)) {
				
				Optional<Login> datosLogin=loginRepository.findById((long) reservaActual.getIdLogin());
				login=datosLogin.get();			
				reservaActual.setFechaModificacion(utilitarios.ObtenerFechaActual());
				
				int difDias = CalcularDiferenciaDias(reservaActual);	
				
				if( (login.getTipouser().equals(Constantes.VALOR_CLIENTE) || 
						login.getTipouser().equals(Constantes.VALOR_EMPLEADO)) && difDias > 6) {
					reservaActual.setFechaReserva(reserva.getFechaReserva());
					reservaActual.setHora(reserva.getHora());
					reservaActual.setCantPersonas(reserva.getCantPersonas());
					reservaActual.setNombres(reserva.getNombres());
					reservaActual.setApellido(reserva.getApellido());
					reservaActual.setTelefono(reserva.getTelefono());
					reservaActual.setIdPaquete(reserva.getIdPaquete());
					reservaActual.setAcompaniante(reserva.getAcompaniante());
					reservaActual.setTotalPago(reserva.getTotalPago());
						
					DiferenciarUsuarioModificacion(reservaActual);
					
					return reservaService.guardarReserva(reservaActual);
				}else {
					throw new MrJoyException("COD03","Estimado cliente, su reserva se no se puede modificar ya que esta a 1 semana de empezar");
				}			
			}
			else {
				throw new MrJoyException("COD04","Estimado Cliente, no se puede actualizar una reserva caducada o cancelada");
			}
			
		}catch(MrJoyException e) {
			throw e;
		}catch (Exception e) {
			throw new ErrorException();
		}
		
	}

	


	@DeleteMapping("/reservas/{id}")
	public void delete(@PathVariable Long id) {
		reservaService.delete(id);
	}
		
	@GetMapping("/reservas-fecha/{fecha}")
	public List<Reserva> listarPorFecha(@PathVariable Date fecha) {
		return reservaService.ListarPorFecha(fecha);
	}
	
	@GetMapping("/reservas-idLogin/{idLogin}")
	public List<Reserva> listarPorIdLogin(@PathVariable int idLogin) {
		return reservaService.listarPorIdLogin(idLogin);
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
	
	private void DiferenciarUsuarioModificacion(Reserva reservaActual) {
		Cliente cliente;
		Empleado empleado;
		if(reservaActual.getFlagTipoReserva().equals(Constantes.FLAG_CLIENTE)) {
			cliente=clienteRepository.findByIdLogin((long) reservaActual.getIdLogin());
			reservaActual.setUsuarioModificacion(cliente.getNombres()+" "+cliente.getApePaterno());
		}
		
		if(reservaActual.getFlagTipoReserva().equals(Constantes.FlAG_EMPLEADO)) {
			empleado= empleadoRespository.findByIdLogin((long) reservaActual.getIdLogin());
			reservaActual.setUsuarioModificacion(empleado.getNombres()+" "+empleado.getApellidos());
		}
	}
	
}
