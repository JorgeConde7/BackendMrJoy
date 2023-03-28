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
	public List<Contacto> index() throws Exception{
		return contactoService.findAll();
	}
	
	@GetMapping("/contactos/{id}")
	public List<Contacto> listarxIdLogin(@PathVariable Long id) throws Exception {
		return contactoService.listarXidLogin(id);
	}
	
	@PostMapping("/contactos")
	public Contacto create(@RequestBody Contacto contacto) throws Exception {
		return contactoService.save(contacto);
	}	

	@PutMapping("/contactos/{id}")
	public Contacto update(@RequestBody Contacto contacto,@PathVariable Long id) throws Exception{
		return contactoService.responder(contacto, id);
	}
			

}
