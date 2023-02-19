package com.example.demo.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.dto.DataResponseDTO;
import com.example.demo.model.Login;
import com.example.demo.model.response.DataResponse;
import com.example.demo.service.LoginService;
import com.example.demo.util.Constantes;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/apilogin")

public class LoginRestController {

	@Autowired
	private LoginService loginService;

	@GetMapping("/login")
	public List<Login> listar() {
		return loginService.findAll();
	}


	@GetMapping("login/{user}/{contrasenia}/{tipouser}")
	public DataResponse<String> auth(@PathVariable String user, @PathVariable String contrasenia,@PathVariable String tipouser) {
		DataResponse<String> response = new DataResponse<>();
		String tipousuario=null;
		try {
			if(tipouser.equals(Constantes.FlAG_EMPLEADO)) {
				tipousuario=Constantes.VALOR_EMPLEADO;
			}else if(tipouser.equals(Constantes.FLAG_CLIENTE)) {
				tipousuario=Constantes.VALOR_CLIENTE;
			}else {
				tipousuario=Constantes.VALOR_ADMIN;
			}
			Login userFound = loginService.validarUsuario(user, contrasenia,tipousuario);
						
			if (userFound == null) {
				response.setStatus(404);
				response.setMessage("Usuario y/o contraseña incorrectos");
				return response;
			}					
			
			String token = loginService.generateToken(userFound); 
		
			response.setData(token);
			response.setStatus(200);
			response.setMessage("Usuario encontrado");

			return response;
		} catch (Exception e) {
			System.out.println(e);
			response.setStatus(500);
			response.setMessage("Error del servidor");
			return response;
		}
	}

	@PostMapping("/login")
	public Login crear(@RequestBody Login login) {
		List<Login> listalogin = loginService.findAll();
		for (Login log : listalogin) {

			// System.out.println("");

			if (login.getUsuario().equals(log.getUsuario()) || login.getUsuario().contains(" ")) {

				Login logeofail = new Login();
				logeofail.setUsuario("El usuario ya está registrado");
				return logeofail;
			}
		}

		return loginService.create(login);
	}

	@PutMapping("/login/{id}")
	public void actualizar(@PathVariable Long id, @RequestBody Login nuevo) {
		Login antiguo = loginService.listarporId(id);
		antiguo.setUsuario(nuevo.getUsuario());
		antiguo.setContrasenia(nuevo.getContrasenia());
		antiguo.setTipouser(nuevo.getTipouser());
		loginService.create(antiguo);
	}

	@DeleteMapping("/login/{id}")
	public void eliminar(Long id) {
		loginService.delete(id);
	}

}
