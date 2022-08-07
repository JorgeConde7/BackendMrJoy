package com.example.demo.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Tarjeta;
import com.example.demo.service.TarjetaService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/apitarjeta")
public class TarjetaRestController 
{
	@Autowired
	private TarjetaService tarjetaService;
	
	@GetMapping("/{num}/{mes}/{cod}")
    public ResponseEntity<?> obtener(@PathVariable String num, @PathVariable String mes, @PathVariable String cod)
	{
		try
		{
			if (tarjetaService.saldo(num, mes, cod) != null) return ResponseEntity.status(HttpStatus.OK).body(tarjetaService.saldo(num, mes, cod));
			else return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("{\"Error\"*:\"Error. Tarjeta no encontrada.\"}");
        }catch (Exception e)
		{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"Error\"*:\"Error "+e.getMessage()+" \"}");
        }
    }
	
	@PutMapping("/guardartarjeta/{num}")
	public Tarjeta actualizar(@PathVariable String num, @RequestBody Tarjeta tarjeta) 
	{
		Tarjeta tarjetaantiguo = tarjetaService.findById(num);
		tarjetaantiguo.setSaldo(tarjeta.getSaldo());
		
		return tarjetaService.save(tarjetaantiguo);
	}
}
