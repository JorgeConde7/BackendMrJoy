package com.example.demo.restController;

import java.util.List;

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

import com.example.demo.model.Login;
import com.example.demo.model.response.DataResponse;
import com.example.demo.service.LoginService;

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

	@GetMapping("/{user}/{contrasenia}")
	public Login obtener(@PathVariable String user, @PathVariable String contrasenia) {
		return loginService.listarLoginUser(user, contrasenia);
	}

	@GetMapping("login/{user}/{contrasenia}")
	public DataResponse<Login> auth(@PathVariable String user, @PathVariable String contrasenia) {
		DataResponse<Login> response = new DataResponse<>();
		try {
			Login userFound = loginService.listarLoginUser(user, contrasenia);

			if (userFound == null || userFound.getIdLogin() == null) {
				response.setStatus(404);
				response.setMessage("Usuario y/o contraseña incorrecto");
				return response;
			}

			response.setData(userFound);
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
