package com.example.demo.restController;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Paquetes;
import com.example.demo.model.Reserva;
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

	// mostrar id
	@GetMapping("/reservas/{id}")
	public Reserva show(@PathVariable Long id) {
		return reservaService.findById(id);
	}

	// crear id
	@PostMapping("/reservas")
	public Reserva create(@RequestBody Reserva reserva) {
		return reservaService.save(reserva);
	}

	// actualizar
	@PutMapping("/reservas/{id}")
	public Reserva update(@RequestBody Reserva reserva, @PathVariable Long id) {
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

		return reservaService.save(reservaActual);
	}

	@DeleteMapping("/reservas/{id}")
	public void delete(@PathVariable Long id) 
	{
		reservaService.delete(id);
	}
	
	@GetMapping("/paquetes")
    public List<Paquetes>listarPaquetes() throws Exception{

        return paqueteService.listarPaquetes();
   

	}
	
	@GetMapping("/reservas-fecha/{fecha}")
	public List<Reserva> listarPorFecha(@PathVariable Date fecha) {
		return reservaService.ListarPorFecha(fecha);
	}
}
