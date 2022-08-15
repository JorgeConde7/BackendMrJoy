package com.example.demo.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.DatosEmpresaDTO;
import com.example.demo.service.DatosEmpresaService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/data")
public class DatosEmpresaRestController {

	@Autowired
	private DatosEmpresaService datosEmpresaService;
	
	@GetMapping("/datosEmpresa")
    public List<DatosEmpresaDTO> datosEmpresa() throws Exception{
        return datosEmpresaService.datosEmpresa();
	}
}
