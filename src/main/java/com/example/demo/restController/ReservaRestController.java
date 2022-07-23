package com.example.demo.restController;

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

import com.example.demo.model.Reserva;
import com.example.demo.service.ReservaService;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/apireserva")
public class ReservaRestController {
	@Autowired
	private ReservaService reservaService;

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
		reservaActual.setFecha_registro(reserva.getFecha_registro());
		reservaActual.setFecha_reserva(reserva.getFecha_reserva());
		reservaActual.setHora(reserva.getHora());
		reservaActual.setCant_personas(reserva.getCant_personas());
		reservaActual.setId_login(reserva.getId_login());
		reservaActual.setNombres(reserva.getNombres());
		reservaActual.setApellido(reserva.getApellido());
		reservaActual.setTelefono(reserva.getTelefono());
		reservaActual.setFlag_tipo_reserva(reserva.getFlag_tipo_reserva());

		return reservaService.save(reservaActual);
	}

	@DeleteMapping("/reservas/{id}")
	public void delete(@PathVariable Long id) 
	{
		reservaService.delete(id);
	}
}
