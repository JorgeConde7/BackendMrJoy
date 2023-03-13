package com.example.demo.restController;

import java.sql.Date;
import java.util.List;

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
import com.example.demo.model.Cliente;
import com.example.demo.model.Empleado;
import com.example.demo.model.Paquetes;
import com.example.demo.model.Reserva;
import com.example.demo.model.response.DataResponse;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.EmpleadoRespository;
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
			
			Cliente cliente= null;
			Empleado empleado=null;
			Utilitarios utilitarios= new Utilitarios();
			
			Reserva reservaActual = reservaService.findById(id);				
			reservaActual.setFechaReserva(reserva.getFechaReserva());
			reservaActual.setHora(reserva.getHora());
			reservaActual.setCantPersonas(reserva.getCantPersonas());
			reservaActual.setNombres(reserva.getNombres());
			reservaActual.setApellido(reserva.getApellido());
			reservaActual.setTelefono(reserva.getTelefono());
			reservaActual.setIdPaquete(reserva.getIdPaquete());
			reservaActual.setAcompaniante(reserva.getAcompaniante());
			reservaActual.setTotalPago(reserva.getTotalPago());
			reservaActual.setFechaModificacion(utilitarios.ObtenerFechaActual());
			
			if(reservaActual.getFlagTipoReserva().equals(Constantes.FLAG_CLIENTE)) {
				cliente=clienteRepository.findByIdLogin((long) reservaActual.getIdLogin());
				reservaActual.setUsuarioModificacion(cliente.getNombres()+" "+cliente.getApePaterno());
			}
			
			if(reservaActual.getFlagTipoReserva().equals(Constantes.FlAG_EMPLEADO)) {
				empleado= empleadoRespository.findByIdLogin((long) reservaActual.getIdLogin());
				reservaActual.setUsuarioModificacion(empleado.getNombres()+" "+empleado.getApellidos());
			}
			

			return reservaService.guardarReserva(reservaActual);
		}catch (Exception e) {
			throw new ErrorException();
		}
		
	}

	@DeleteMapping("/reservas/{id}")
	public void delete(@PathVariable Long id) {
		reservaService.delete(id);
	}
	
	@GetMapping("/paquetes")
    public List<Paquetes>listarPaquetes() throws Exception{
		return paqueteService.listarPaquetes();
	}
	
	@GetMapping("/totalPaquetes")
    public List<TotalVentasDTO> totalPaquetes() throws Exception{
        return paqueteService.totalPaquetes();
	}
	
	@GetMapping("/reservas-fecha/{fecha}")
	public List<Reserva> listarPorFecha(@PathVariable Date fecha) {
		return reservaService.ListarPorFecha(fecha);
	}
	
	@GetMapping("/reservas-idLogin/{idLogin}")
	public List<Reserva> listarPorIdLogin(@PathVariable int idLogin) {
		return reservaService.listarPorIdLogin(idLogin);
	}
}
