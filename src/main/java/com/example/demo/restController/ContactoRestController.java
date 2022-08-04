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
	public Contacto show(@PathVariable Long id) {
		return contactoService.findById(id);
	}
	
	//Método para crear:
		@PostMapping("/contactos")
		public Contacto create(@RequestBody Contacto contacto) {
			return contactoService.save(contacto);
		}
		
	//Método para estado:
	@PutMapping("/contactos/{id}")
		public Contacto update(@RequestBody Contacto contacto,@PathVariable Long id) {
		Contacto ContactoActual = contactoService.findById(id);
				
			ContactoActual.setNombres(contacto.getNombres());
			ContactoActual.setCorreo(contacto.getCorreo());
			ContactoActual.setTelefono(contacto.getTelefono());
			ContactoActual.setAsunto(contacto.getAsunto());
			ContactoActual.setEstado(contacto.getEstado());
			ContactoActual.setDescripcion(contacto.getDescripcion());
			return contactoService.save(ContactoActual);
		}
			
	//Método para eliminar:
	@DeleteMapping("/contactos/{id}")
	public void delete(@PathVariable Long id) {
		contactoService.delete(id);
	}
}
