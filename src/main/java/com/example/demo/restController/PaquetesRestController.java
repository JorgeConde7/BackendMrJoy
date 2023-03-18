package com.example.demo.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TotalVentasDTO;
import com.example.demo.model.Paquetes;
import com.example.demo.service.PaqueteService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/apipaquetes")
public class PaquetesRestController {
	
	@Autowired
	PaqueteService paqueteService;
	
	@GetMapping("/paquetes")
    public List<Paquetes>listarPaquetes() throws Exception{
		return paqueteService.listarPaquetes();
	}
	
	@PostMapping("/paquetes")
	public Paquetes crear(@RequestBody Paquetes paquetes) throws Exception {
		return paqueteService.guardarPaquete(paquetes);
	}
	
	@PutMapping("/paquetes/{id}")
	public Paquetes actualizar(@PathVariable Integer id, @RequestBody Paquetes paquetes) throws Exception {
		return paqueteService.update(id, paquetes);
	}
	
	@GetMapping("/totalPaquetes")
    public List<TotalVentasDTO> totalPaquetes() throws Exception{
        return paqueteService.totalPaquetes();
	}


   
}
