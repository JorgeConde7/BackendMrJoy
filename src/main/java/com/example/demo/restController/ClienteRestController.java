package com.example.demo.restController;

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

import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/rest-v1")
public class ClienteRestController {
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping("listar-clientes")
    public ResponseEntity<?>listarCliente(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarCliente());
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Error\"*:\"Error. Por favor intente mas tarde.\"}");
        }
    }
	
	@GetMapping("listar-clientes/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id));
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\"*:\"No existe Cliente\"}");
        }
    }
	
	@PostMapping("guardar-cliente")
    public ResponseEntity<?> save (@RequestBody  Cliente model){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.save(model));
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\"*:\"Error. Por favor intente mas tarde.\"}");
        }
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id,@RequestBody Cliente cliente){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.update(id,cliente));
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\"*:\"Error. Por favor intente mas tarde.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete( @PathVariable Integer id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.delete(id));
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\"*:\"Error. Por favor intente mas tarde.\"}");
        }
    }
	
}
