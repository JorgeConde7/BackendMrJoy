package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;

@Service(value="clienteService")
public class ClienteServiceImpl implements ClienteService {

	@Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional
    public List<Cliente> listarCliente() throws Exception{
        try{
            List<Cliente> clientes= clienteRepository.findAll();
            return clientes;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Cliente findById(Integer id)throws Exception {
        try{
            Optional<Cliente> clienteOptional= clienteRepository.findById(id);
            return clienteOptional.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Cliente save(Cliente model) throws Exception{
        try{
            model=clienteRepository.save(model);
            return model;

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Cliente update(Integer id, Cliente model)throws Exception {
        try{
            Optional<Cliente> clienteOptional= clienteRepository.findById(id);
            Cliente cliente= clienteOptional.get();
            model.setIdCliente(id);
            cliente=clienteRepository.save(model);
            
            return cliente;
        }catch (Exception e){
            throw new Exception(e.getMessage());
            
        }
    }

    @Override
    @Transactional
    public boolean delete(Integer id)throws Exception {
        try {
            if (clienteRepository.existsById(id)) {
            	clienteRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
