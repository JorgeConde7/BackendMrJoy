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

import com.example.demo.dto.TotalVentasDTO;
import com.example.demo.model.Paquetes;
import com.example.demo.model.Parque;
import com.example.demo.service.ParquesService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/apiParques")
public class ParquesRestController {

	@Autowired
	ParquesService parquesService;
	
	@GetMapping("/parques")
    public List<Parque>listarPaquetes() throws Exception{
		return parquesService.listarParques();
	}
	
	@PostMapping("/parques")
	public Parque crear(@RequestBody Parque parque) throws Exception {
		return parquesService.guardarParque(parque);
	}
	
	@PutMapping("/parques/{id}")
	public Parque actualizar(@PathVariable Integer id, @RequestBody Parque parque) throws Exception {
		return parquesService.actualizarParque(id, parque);
	}
	
	@DeleteMapping("/parques/{id}")
	public void eliminar(@PathVariable Integer id) throws Exception {
		 parquesService.eliminarParque(id);
	}

}
