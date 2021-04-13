package com.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.hospital.model.PlasmaDonation;
import com.hospital.model.VaccineRegistered;
import com.hospital.service.PlasmaDonationService;
import com.hospital.service.UserServices;
import com.hospital.service.VaccineRegisteredServices;

@RestController
@CrossOrigin
public class VaccineUserController {
	@Autowired
	private VaccineRegisteredServices vaccineRegisteredService;
	@Autowired
	private PlasmaDonationService plasmaDonationService;
	@Autowired
	private UserServices userServices;
	
	@GetMapping("/getAllVaccineRegistered")
	public List<VaccineRegistered> getAllVaccineRegisteredUsers() {
		return vaccineRegisteredService.getAllVaccineRegistered();
	}
	@GetMapping("/getVaccineRegisteredByState/{state}")
	public List<VaccineRegistered> getVaccinedUserByState(@PathVariable String state) {
		return vaccineRegisteredService.getUserByState(state);
	}
	
	@GetMapping("/getUsertByName/{username}")
	public List<VaccineRegistered> getVaccineRequestByName(@PathVariable String username) {
		return vaccineRegisteredService.getVaccineRegisteredByName(username);
	}
	
	
	@DeleteMapping("/deleteVaccineUser/{id}")
	public List<VaccineRegistered> deleteVaccine(@PathVariable Long id) {
		vaccineRegisteredService.deletedVaccineUser(id);
		//userServices.deleteUser(id);
		return vaccineRegisteredService.getAllVaccineRegistered();
	}
	
	@PutMapping("/updateUserRequest")
	public VaccineRegistered updateDonationRequest(@RequestBody VaccineRegistered user) {
		System.out.println("Update user"+user.toString());
		
		return vaccineRegisteredService.updateUserRequest(user);
	}
	
	
	@PostMapping("/addPlasmaDonotor")
	
	public String addPlasmaDonator(@RequestBody PlasmaDonation donator) {
		donator.setStatus("0");
		donator=plasmaDonationService.saveDonor(donator);
		return "Success";
	}

}
