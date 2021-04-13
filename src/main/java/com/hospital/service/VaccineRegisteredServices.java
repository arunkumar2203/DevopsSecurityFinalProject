package com.hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hospital.model.VaccineRegistered;
import com.hospital.repository.VaccineRegisteredRepository;

@Service
public class VaccineRegisteredServices {
	@Autowired
	private VaccineRegisteredRepository vaccineRepository;
	
	public VaccineRegistered saveVaccineUser(VaccineRegistered vaccineUser) {
		return vaccineRepository.save(vaccineUser);

	}
	
	public List<VaccineRegistered> getAllVaccineRegistered() {
		return vaccineRepository.findAll();

	} 
	
	public List<VaccineRegistered> getUserByState(String state) {
		return  vaccineRepository.findByState(state);

	} 
	public String deletedVaccineUser(Long id) {
		vaccineRepository.deleteById(id);
		return "Deleted  Vaccine user "+id;
	}
	public List<VaccineRegistered> getVaccineRegisteredByName(String username) { 
		return vaccineRepository.findByName(username);

	}
	
	public VaccineRegistered updateUserRequest(VaccineRegistered existingUser) {
		VaccineRegistered existingUserRequest=vaccineRepository.findById(existingUser.getId()).orElse(null);

		existingUserRequest.setFirstDose(existingUser.getFirstDose());
		existingUserRequest.setSecondDose(existingUser.getSecondDose());

		return vaccineRepository.save(existingUserRequest);

	}



}
