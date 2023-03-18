package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Parque;

@Repository
public interface ParquesRepository extends JpaRepository<Parque, Integer> {

	
	
	
}
