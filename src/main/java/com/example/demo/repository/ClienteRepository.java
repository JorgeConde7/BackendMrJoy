package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	List<Cliente> findByNombresContainingOrApellidosContaining(String nombre, String apellido);

    @Query(value="SELECT p FROM Cliente p WHERE p.nombres LIKE :filtro OR p.apellidos LIKE :filtro")
    List<Cliente> search (@Param("filtro") String filtro);


}
