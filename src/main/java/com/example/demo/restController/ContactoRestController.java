package com.example.demo.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.model.Contacto;
import com.example.demo.service.ContactoService;

@CrossOrigin(origins= {"*"})
@RestController
@RequestMapping("/api")
public class ContactoRestController {
	
	@Autowired
	private ContactoService contactoService;

	@GetMapping("/contactos")
	public List<Contacto> index(){
		return contactoService.findAll();
	}
	
	//Método para mostrar por Id:
	@GetMapping("/contactos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Contacto contacto =contactoService.findById(id);
		if (contacto!=null) {
			return new ResponseEntity<>(contacto, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	//Método para crear:
	@PostMapping("/contactos")
	public Contacto create(@RequestBody Contacto contacto) {
		return contactoService.save(contacto);
	}
		
	//Método para estado:
	@PutMapping("/contactos/{id}")
		public ResponseEntity<?> update(@RequestBody Contacto contacto,@PathVariable Long id) {
		Contacto ContactoActual = contactoService.findById(id);
		if (contacto != null) {
			ContactoActual.setNombres(contacto.getNombres());
			ContactoActual.setCorreo(contacto.getCorreo());
			ContactoActual.setTelefono(contacto.getTelefono());
			ContactoActual.setAsunto(contacto.getAsunto());
			ContactoActual.setEstado(contacto.getEstado());
			ContactoActual.setDescripcion(contacto.getDescripcion());
			ContactoActual.setFechaRegistro(contacto.getFechaRegistro());
			contactoService.save(ContactoActual);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
			
	//Método para eliminar:
	@DeleteMapping("/contactos/{id}")
	public void delete(@PathVariable Long id) {
		contactoService.delete(id);
	}
}
