package com.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.model.PlasmaDonation;
import com.hospital.model.VaccineRegistered;

public interface PlasmaDonationRepository extends JpaRepository<PlasmaDonation, Long>{

}
