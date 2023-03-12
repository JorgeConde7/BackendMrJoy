package com.example.demo.restController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.dto.ErrorDTO;
import com.example.demo.exception.ErrorException;
import com.example.demo.exception.MrJoyException;


@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(value=ErrorException.class)
	public ResponseEntity<ErrorDTO> errorE(ErrorException e) {
		ErrorDTO errorDTO= new ErrorDTO();
		errorDTO.setCode(e.getCodeError());
		errorDTO.setMessage(e.getMessage());
		return new ResponseEntity<>(errorDTO,e.getStatus());	
	}
	
	@ExceptionHandler(value=MrJoyException.class)
	public ResponseEntity<ErrorDTO> requestException(MrJoyException e) {
		ErrorDTO errorDTO= new ErrorDTO();
		errorDTO.setCode(e.getCodeError());
		errorDTO.setMessage(e.getMessage());
		/*return new ResponseEntity<>(errorDTO,HttpStatus.CREATED);*/
		return new ResponseEntity<>(errorDTO,e.getStatus());
	}
	
}
