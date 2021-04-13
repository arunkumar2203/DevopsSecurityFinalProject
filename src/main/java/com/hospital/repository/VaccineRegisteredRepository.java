package com.hospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.model.VaccineRegistered;



public interface VaccineRegisteredRepository extends JpaRepository<VaccineRegistered, Long>{
	List<VaccineRegistered> findByState(String state);
	List<VaccineRegistered> findByName(String name);

}
