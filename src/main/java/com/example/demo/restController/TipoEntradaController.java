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

import com.example.demo.model.TipoEntrada;

import com.example.demo.service.TipoEntradaService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController

@RequestMapping("/apitipoentrada")
public class TipoEntradaController {
	
	@Autowired
	private TipoEntradaService tipoentradaservice;
	
	@GetMapping("/tipoentrada")
	public List<TipoEntrada> listar(){
		return tipoentradaservice.findAll();
	}
	
	@GetMapping("/tipoentrada/{id}")
	public TipoEntrada detalle(@PathVariable Long id) {
		return tipoentradaservice.findById(id);
	}
	
	@PostMapping("/tipoentrada")
	public TipoEntrada crear(@RequestBody TipoEntrada tipoentrada) {
		return tipoentradaservice.save(tipoentrada);
	}
	
	@PutMapping("/tipoentrada/{id}")
	public TipoEntrada actualizar(@PathVariable Long id,
		@RequestBody TipoEntrada tipoentrada) {
		TipoEntrada antiguo=tipoentradaservice.findById(id);
		antiguo.setDescripcion(tipoentrada.getDescripcion());
		
		return tipoentradaservice.save(antiguo);
	}
	
	@DeleteMapping("/tipoentrada/{id}")
	public void borrar(@PathVariable Long id) {
		tipoentradaservice.delete(id);
	}

}
