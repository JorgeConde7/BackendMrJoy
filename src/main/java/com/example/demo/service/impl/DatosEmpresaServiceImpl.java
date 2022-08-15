package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DatosEmpresaDTO;
import com.example.demo.model.Cliente;
import com.example.demo.model.Contacto;
import com.example.demo.model.Empleado;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ContactoRepository;
import com.example.demo.repository.EmpleadoRespository;
import com.example.demo.service.DatosEmpresaService;

@Service
public class DatosEmpresaServiceImpl implements DatosEmpresaService {

	@Autowired
	private EmpleadoRespository empleadoRespository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ContactoRepository contactoRepository;
	@Override
	public List<DatosEmpresaDTO> datosEmpresa() throws Exception {
		 List<Empleado> listEmpleados= empleadoRespository.findAll();
		 List<Cliente> listClientes= (List<Cliente>) clienteRepository.findAll();
		 List<Contacto> listContactos= (List<Contacto>) contactoRepository.findAll();

		 
		 List<DatosEmpresaDTO> listDatosEmpresaDTOs= new ArrayList<DatosEmpresaDTO>();
		 
		 DatosEmpresaDTO datosEmpresaDTO = new DatosEmpresaDTO();
		 datosEmpresaDTO.setDato("Empleados");
		 datosEmpresaDTO.setCantidad(listEmpleados.size());
		 
		 DatosEmpresaDTO datosEmpresaDTO1 = new DatosEmpresaDTO();
		 datosEmpresaDTO1.setDato("Clientes");
		 datosEmpresaDTO1.setCantidad(listClientes.size());
		 
		 DatosEmpresaDTO datosEmpresaDTO2 = new DatosEmpresaDTO();
		 datosEmpresaDTO2.setDato("Consultas");
		 datosEmpresaDTO2.setCantidad(listContactos.size());
		 
		 listDatosEmpresaDTOs.add(datosEmpresaDTO);
		 listDatosEmpresaDTOs.add(datosEmpresaDTO1);
		 listDatosEmpresaDTOs.add(datosEmpresaDTO2);
		 
		return listDatosEmpresaDTOs;
	}

}
