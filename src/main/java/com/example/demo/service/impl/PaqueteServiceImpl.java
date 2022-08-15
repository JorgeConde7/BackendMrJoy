package com.example.demo.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.TotalVentasDTO;
import com.example.demo.model.Paquetes;
import com.example.demo.model.Reserva;
import com.example.demo.repository.PaqueteRepository;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.service.PaqueteService;

@Service
public class PaqueteServiceImpl implements PaqueteService {
	
	@Autowired
	private PaqueteRepository paqueteRepository;
	@Autowired
	private ReservaRepository reservaRepository;

	@Override
	public List<Paquetes> listarPaquetes() throws Exception {
		try{
            List<Paquetes> boleta= paqueteRepository.findAll();
            return boleta;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
	}

	@Override
	public List<TotalVentasDTO> totalPaquetes() throws Exception {
		try {
			List<Reserva> ListReserva= (List<Reserva>) reservaRepository.findAll();
			double totalPaqNito=0;
			double totalPaqMrJoy=0;
			double totalPaqSuperJoy=0;
			
			for (Reserva datos : ListReserva) {
				if(datos.getIdPaquete()==1) {
					double totalAcomp=datos.getAcompaniante()*6;
					totalPaqNito=totalPaqNito+(datos.getCantPersonas()*25.9)+totalAcomp;
				}else if(datos.getIdPaquete()==2) {
					double totalAcomp=datos.getAcompaniante()*6;
					totalPaqMrJoy=totalPaqMrJoy+(datos.getCantPersonas()*32.9)+totalAcomp;
				}
				else if(datos.getIdPaquete()==3) {
					double totalAcomp=datos.getAcompaniante()*6;
					totalPaqSuperJoy=totalPaqSuperJoy+(datos.getCantPersonas()*39.9)+totalAcomp;
				}
			}
			
			List<TotalVentasDTO> ListTotalVentasDTO= new ArrayList<>();
			
			TotalVentasDTO totalVentasDTO= new TotalVentasDTO();
			totalVentasDTO.setNombre("Paquete Nito");
			totalVentasDTO.setTotal(totalPaqNito);
			
			TotalVentasDTO totalVentasDTO1= new TotalVentasDTO();
			totalVentasDTO1.setNombre("Paquete Mr. Joy");
			totalVentasDTO1.setTotal(totalPaqMrJoy);
			
			TotalVentasDTO totalVentasDTO2= new TotalVentasDTO();
			totalVentasDTO2.setNombre("Super Mr. Joy");
			totalVentasDTO2.setTotal(totalPaqSuperJoy);
			
			ListTotalVentasDTO.add(totalVentasDTO);
			ListTotalVentasDTO.add(totalVentasDTO1);
			ListTotalVentasDTO.add(totalVentasDTO2);
			
			return ListTotalVentasDTO;
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		

	}
}