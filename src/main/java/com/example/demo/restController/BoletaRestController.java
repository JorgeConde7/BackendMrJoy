package com.example.demo.restController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

import com.example.demo.dto.BoletaDTO;
import com.example.demo.model.BoletaEntrada;
import com.example.demo.service.BoletaService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/apiBoleta")
public class BoletaRestController {
	
	@Autowired
	private BoletaService boletaService;
	
	@GetMapping("listar-boleta")
    public ResponseEntity<?>listarCliente(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(boletaService.listarBoleta());
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Error\"*:\"Error. Por favor intente mas tarde.\"}");
        }
    }
	
	@GetMapping("listar-boleta/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(boletaService.findById(id));
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\"*:\"No existe Cliente\"}");
        }
    }
	
	
	@PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id,@RequestBody BoletaEntrada boleta){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(boletaService.update(id,boleta));
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\"*:\"Error. Por favor intente mas tarde.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete( @PathVariable Integer id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(boletaService.delete(id));
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\"*:\"Error. Por favor intente mas tarde.\"}");
        }
    }
    
    @PostMapping("guardar")
    public ResponseEntity<?> guardarBoleta (@RequestBody  BoletaDTO boleta) throws Exception{
    	Map<String, Object> response= new HashMap<>();
    	
        try{
        	response.put("Mensaje", "Se guardo correctamente la boleta");
          boletaService.guardarBoleta(boleta);
          return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
        }catch (DataAccessException e){
        	return new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
		
    }
}
