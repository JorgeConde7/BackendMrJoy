package com.example.demo.restController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DataResponseDTO;
import com.example.demo.exception.ErrorException;
import com.example.demo.exception.MrJoyException;
import com.example.demo.model.Login;
import com.example.demo.repository.LoginRepository;
import com.example.demo.service.LoginService;
import com.example.demo.util.Constantes;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/apilogin")

public class LoginRestController {

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private LoginService loginService;


	@GetMapping("login/{user}/{contrasenia}/{tipouser}")
	public DataResponseDTO<String> auth(@PathVariable String user, @PathVariable String contrasenia,@PathVariable String tipouser) {
		DataResponseDTO<String> response = new DataResponseDTO<>();
					
		try {
			
			Login userFound= loginRepository.findByUsuarioAndContrasenia(user, contrasenia);
			
			if (userFound == null) {
				throw new MrJoyException("COD08","Usuario y/o contraseña incorrectos");
			}
			
			if(userFound.getTipouser().equals(Constantes.VALOR_CLIENTE) && tipouser.equals(Constantes.FlAG_EMPLEADO)) {
				throw new MrJoyException("COD08","Acceso denegado");
			}
						
			if((userFound.getTipouser().equals(Constantes.VALOR_EMPLEADO) ||
					userFound.getTipouser().equals(Constantes.VALOR_ADMIN)) && tipouser.equals(Constantes.FLAG_CLIENTE)) {
				throw new MrJoyException("COD08","Acceso denegado");
			}

			String token = loginService.generateToken(userFound); 		
			response.setData(token);
			response.setStatus(200);
			response.setMessage("Usuario encontrado");
			return response;
			
		}catch(MrJoyException e) {
			throw e;
		}catch(Exception e) {
			throw new ErrorException();
		}
	}

	@PostMapping("/login")
	public Login crear(@RequestBody Login login) throws Exception {	
			return loginService.crearUsuario(login);
	}

	@PutMapping("/login/{id}")
	public void actualizar(@PathVariable Long id, @RequestBody Login nuevo) throws Exception {
		Login antiguo = loginService.listarporId(id);
		antiguo.setUsuario(nuevo.getUsuario());
		antiguo.setContrasenia(nuevo.getContrasenia());
		antiguo.setTipouser(nuevo.getTipouser());
		loginService.crearUsuario(antiguo);
	}



}
