package com.example.demo.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.LoginService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/apilogin")

public class LoginRestController {
	
	@Autowired
	private LoginService loginService;

	
	@GetMapping("login/{user}")
    public ResponseEntity<?> getOne(@PathVariable String user){
        try{
        	
			return ResponseEntity.status(HttpStatus.OK).body(loginService.listarLoginUser(user));
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\"*:\"No existe Cliente\"}");
        }
    }
	
}
