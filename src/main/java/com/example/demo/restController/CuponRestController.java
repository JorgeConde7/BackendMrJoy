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

import com.example.demo.model.Cupon;
import com.example.demo.model.Promociones;
import com.example.demo.service.CuponService;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class CuponRestController {
	@Autowired
	private CuponService cuponservice;
	
	@GetMapping("/cupon")
	public List<Cupon> listar(){
		return cuponservice.findAll();
	}
	
	@GetMapping("/cupon/{id}")
	public Cupon detalle(@PathVariable Long id) {
		return cuponservice.findById(id);
	}
	
	@PostMapping("/cupon")
	public Cupon crear(@RequestBody Cupon cupon) {
		return cuponservice.save(cupon);
	}
	
	@PutMapping("/cupon/{id}")
	public Cupon actualizar(@PathVariable Long id,
		@RequestBody Cupon cupon) {
		Cupon antiguo=cuponservice.findById(id);
		antiguo.setCupon(cupon.getCupon());
		
		return cuponservice.save(antiguo);
	}
	
	
	@DeleteMapping("/cupon/{id}")
	public void borrar(@PathVariable Long id) {
		cuponservice.delete(id);
	}

}
