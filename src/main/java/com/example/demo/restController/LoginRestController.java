package com.example.demo.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Login;
import com.example.demo.service.LoginService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/apilogin")

public class LoginRestController {
	
	@Autowired
	private LoginService loginService;

	
	@GetMapping("/{user}/{contrasenia}")
    public Login obtener(@PathVariable String user,@PathVariable String contrasenia ){
		return loginService.listarLoginUser(user,contrasenia);
    }
	
	@PostMapping("/login")
	public Login crear(@RequestBody Login login) {
		List<Login> listalogin=loginService.findAll();
		for (Login log : listalogin) {
			System.out.println("=============================================================================");
			System.out.println(log.getUsuario()+"|"+login.getUsuario());
			//System.out.println("");

			if (login.getUsuario().equals(log.getUsuario()) || login.getUsuario().contains(" ")) {
				System.out.println("asdasd");
				Login logeofail=new Login();
				logeofail.setUsuario("El usuario ya está registrado");
				return logeofail;
			}
		}
		
		return loginService.create(login);
	}
	
	
}
