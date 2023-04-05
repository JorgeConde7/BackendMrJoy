package com.example.demo.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.dto.EmpleadoDTO;
import com.example.demo.model.Empleado;

import com.example.demo.service.EmpleadoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class EmpleadoRestController {
	@Autowired
	private EmpleadoService empleadoService;

	@GetMapping("/empleados")
	public List<Empleado> index() {
		return empleadoService.findAll();

	}

	@GetMapping("/empleados/{id}")
	public Empleado show(@PathVariable Long id) {
		return empleadoService.findById(id);
	}

	@PostMapping("/guardarEmpleado")
	public ResponseEntity<EmpleadoDTO> createEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
		
			return empleadoService.guardarDatos(empleadoDTO);
		
	}
	
	@PutMapping("/empleados/{id}")
	public Empleado update(@RequestBody Empleado empleado, @PathVariable Long id) {
		Empleado empleadoActual = empleadoService.findById(id);
		empleadoActual.setNombres(empleado.getNombres());
		empleadoActual.setApellidos(empleado.getApellidos());
		empleadoActual.setDni(empleado.getDni());
		empleadoActual.setTelefono(empleado.getTelefono());
		empleadoActual.setCorreo(empleado.getCorreo());
		empleadoActual.setTurno(empleado.getTurno());
		return empleadoService.save(empleadoActual);
	}
	
	@DeleteMapping("/empleados/{id}")
	public void delete(@PathVariable Long id) {
		empleadoService.delete(id);
	}
	
}

