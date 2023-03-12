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
			List<Reserva> listReserva= (List<Reserva>) reservaRepository.findAll();
			List<Paquetes> listPaquete= paqueteRepository.findAll();
			
			List<TotalVentasDTO> listTotalVentasDTO= new ArrayList<>();
			
		// HashMap<Integer, Double> totalPaquetesDic= new HashMap<>();	
		 TotalVentasDTO totalVentasDTO= null;
		 
		 		for(Paquetes paquetes : listPaquete) {
		 			double total= 0; 
		 			for(Reserva reserva:listReserva) {
		 				
		 				if(paquetes.getIdPaquete()==reserva.getIdPaquete()) {	 
		 					
		 					total=total +reserva.getTotalPago();	    
		 				}	
		 			}
		 			System.out.println(paquetes.getDescripcion()+"="+total);
		 			totalVentasDTO= new TotalVentasDTO();
		 			
		 			totalVentasDTO.setNombre(paquetes.getDescripcion());
				    totalVentasDTO.setTotal(total);
				    
				    listTotalVentasDTO.add(totalVentasDTO);		
				}
		
		 /*
				for (Reserva reserva : listReserva) {
					
					if(!totalPaquetesDic.containsKey(reserva.getIdPaquete())) {
						totalPaquetesDic.put(reserva.getIdPaquete(), reserva.getTotalPago());
					}
					else {
						totalPaquetesDic.put(reserva.getIdPaquete(), totalPaquetesDic.get(reserva.getIdPaquete())+reserva.getTotalPago());
					}
								
				}
				
				for (Paquetes paquetes : listPaquete) {
					for (Map.Entry<Integer, Double> entry : totalPaquetesDic.entrySet()) {
					    System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
					    if(paquetes.getIdPaquete()==entry.getKey()) {
					    	totalVentasDTO= new TotalVentasDTO();
						    totalVentasDTO.setNombre(paquetes.getDescripcion());
						    totalVentasDTO.setTotal(entry.getValue());
						    listTotalVentasDTO.add(totalVentasDTO);
					    }			     
					}
				}			
		 */	
			return listTotalVentasDTO;
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		

	}
}