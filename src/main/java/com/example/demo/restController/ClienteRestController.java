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
import com.example.demo.exception.ErrorException;
import com.example.demo.exception.MrJoyException;
import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;

@CrossOrigin(origins= {"*"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> index(){
		return clienteService.findAll();
	}
	
	@GetMapping("/clientes/{id}")
	public Cliente show(@PathVariable Long id) {
		return clienteService.findById(id);
	}
	
	@GetMapping("/clientesbyidlogin/{id}")
	public Cliente showidLogin(@PathVariable Long id) {
		return clienteService.findByIdLogin(id);
	}
	
	@PostMapping("/clientes")
	public Cliente create(@RequestBody Cliente cliente) {
		return clienteService.save(cliente);
	}

	@PutMapping("/clientes/{id}")
	public Cliente update(@RequestBody Cliente cliente,@PathVariable Long id) {
		Cliente ClienteActual = clienteService.findById(id);
		
		ClienteActual.setNombres(cliente.getNombres());
		ClienteActual.setApePaterno(cliente.getApePaterno());
		ClienteActual.setApeMaterno(cliente.getApeMaterno());
		ClienteActual.setTelefono(cliente.getTelefono());
		ClienteActual.setCorreo(cliente.getCorreo());
		ClienteActual.setDireccion(cliente.getDireccion());
		ClienteActual.setGenero(cliente.getGenero());
		ClienteActual.setFechaNacimiento(cliente.getFechaNacimiento());
		ClienteActual.setRutaImg(cliente.getRutaImg());
		ClienteActual.setIdLogin(cliente.getIdLogin());
		return clienteService.save(ClienteActual);
	}
	

	
	@PostMapping("/guardarCliente")
	public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) 
	{
		try {
			return clienteService.guardarDatos(clienteDTO);
		}catch(MrJoyException e) {
			throw e;
		}catch (Exception e) {
			throw new ErrorException();
		}
	}
	
}
