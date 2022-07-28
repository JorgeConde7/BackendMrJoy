package com.example.demo.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.EmpleadoDTO;
import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;

@CrossOrigin(origins= {"http://localhost:4401"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> index(){
		return clienteService.findAll();
	}
	
	//Método para mostrar por Id:
	@GetMapping("/clientes/{id}")
	public Cliente show(@PathVariable Long id) {
		return clienteService.findById(id);
	}
	
	//Método para crear:
	@PostMapping("/clientes")
	public Cliente create(@RequestBody Cliente cliente) {
		return clienteService.save(cliente);
	}
	
	//Método para actualizar:
	@PutMapping("/clientes/{id}")
	public Cliente update(@RequestBody Cliente cliente,@PathVariable Long id) {
		Cliente ClienteActual = clienteService.findById(id);
		
		ClienteActual.setNombres(cliente.getNombres());
		ClienteActual.setApellidos(cliente.getApellidos());
		ClienteActual.setTelefono(cliente.getTelefono());
		ClienteActual.setCorreo(cliente.getCorreo());
		ClienteActual.setDireccion(cliente.getDireccion());
		ClienteActual.setGenero(cliente.getGenero());
		ClienteActual.setFecha_nacimiento(cliente.getFecha_nacimiento());
		ClienteActual.setId_login(cliente.getId_login());
		return clienteService.save(ClienteActual);
	}
	
	//Método para eliminar:
	@DeleteMapping("/clientes/{id}")
	public void delete(@PathVariable Long id) {
		clienteService.delete(id);
	}
	
	@PostMapping("/guardarCliente")
	public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) 
	{
		System.out.println("OEKEE");
		return clienteService.guardarDatos(clienteDTO);
	}
	
}
