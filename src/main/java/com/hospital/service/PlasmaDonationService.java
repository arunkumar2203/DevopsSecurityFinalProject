package com.hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hospital.model.PlasmaDonation;
import com.hospital.model.VaccineRegistered;
import com.hospital.repository.PlasmaDonationRepository;

@Service
public class PlasmaDonationService {
	
	@Autowired
	private PlasmaDonationRepository repository;

	public PlasmaDonation saveDonor(PlasmaDonation donation) {
		return repository.save(donation);

	}
	
	public List<PlasmaDonation> getAllVaccineRegistered() {
		return repository.findAll();

	} 
	
	public PlasmaDonation updateUserRequest(PlasmaDonation existingUser) {
		PlasmaDonation existingUserRequest=new PlasmaDonation();
		try {
		existingUserRequest=repository.findById(existingUser.getId()).orElse(null);

		existingUserRequest.setStatus(existingUser.getStatus());
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return repository.save(existingUserRequest);

	}
	 
}
