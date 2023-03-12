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
import com.example.demo.model.Paquetes;
import com.example.demo.model.Reserva;
import com.example.demo.model.response.DataResponse;
import com.example.demo.service.PaqueteService;
import com.example.demo.service.ReservaService;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/apireserva")
public class ReservaRestController {
	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private PaqueteService paqueteService;

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
		Reserva reservaActual = reservaService.findById(id);
		reservaActual.setFechaRegistro(reserva.getFechaRegistro());
		reservaActual.setFechaReserva(reserva.getFechaReserva());
		reservaActual.setHora(reserva.getHora());
		reservaActual.setCantPersonas(reserva.getCantPersonas());
		reservaActual.setIdLogin(reserva.getIdLogin());
		reservaActual.setNombres(reserva.getNombres());
		reservaActual.setApellido(reserva.getApellido());
		reservaActual.setTelefono(reserva.getTelefono());
		reservaActual.setFlagTipoReserva(reserva.getFlagTipoReserva());
		reservaActual.setIdPaquete(reserva.getIdPaquete());
		reservaActual.setAcompaniante(reserva.getAcompaniante());
		reservaActual.setTotalPago(reserva.getTotalPago());

		return reservaService.guardarReserva(reservaActual);
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
}
