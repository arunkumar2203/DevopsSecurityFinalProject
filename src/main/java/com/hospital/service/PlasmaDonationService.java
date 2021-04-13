package com.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hospital.model.PlasmaDonation;
import com.hospital.repository.PlasmaDonationRepository;

@Service
public class PlasmaDonationService {
	
	@Autowired
	private PlasmaDonationRepository repository;

	public PlasmaDonation saveDonor(PlasmaDonation donation) {
		return repository.save(donation);

	}
}
