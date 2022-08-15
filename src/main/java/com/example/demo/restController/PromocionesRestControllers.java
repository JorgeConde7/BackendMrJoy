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

import com.example.demo.model.Promociones;
import com.example.demo.service.PromocionesService;



@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class PromocionesRestControllers {
	@Autowired
	private PromocionesService promocionesservice;
	
	@GetMapping("/promociones")
	public List<Promociones> listar(){
		return promocionesservice.findAll();
	}
	
	@GetMapping("/promociones/{id}")
	public Promociones detalle(@PathVariable Long id) {
		return promocionesservice.findById(id);
	}
	
	@PostMapping("/promociones")
	public Promociones crear(@RequestBody Promociones promociones) {
		return promocionesservice.save(promociones);
	}
	
	@PutMapping("/promociones/{id}")
	public Promociones actualizar(@PathVariable Long id,
		@RequestBody Promociones tipoentrada) {
		Promociones antiguo=promocionesservice.findById(id);
		antiguo.setPromociones(tipoentrada.getPromociones());
		antiguo.setDescripcion(tipoentrada.getDescripcion());
		antiguo.setFoto(tipoentrada.getFoto());
		
		return promocionesservice.save(antiguo);
	}
	
	@DeleteMapping("/promociones/{id}")
	public void borrar(@PathVariable Long id) {
		promocionesservice.delete(id);
	}

}
