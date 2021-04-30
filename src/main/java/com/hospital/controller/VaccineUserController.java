package com.hospital.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	@Autowired
    private JavaMailSender mailSender;
	
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
		sendingVaccineEmailtoUsers(user);
		
		return vaccineRegisteredService.updateUserRequest(user);
	}
	
	
	@PostMapping("/addPlasmaDonotor")
	
	public String addPlasmaDonator(@RequestBody PlasmaDonation donator) {
		donator.setStatus("0");
		donator=plasmaDonationService.saveDonor(donator);
		return "Success";
	}
	
	@GetMapping("/getAllPlasmaRegistered")
	public List<PlasmaDonation> getAllPlasamRegisteredUsers() {
		return plasmaDonationService.getAllVaccineRegistered();
	}
	
	@PutMapping("/updatePlasma")
	public PlasmaDonation updatePlasmaRequest(@RequestBody PlasmaDonation user) {
		System.out.println("Update user"+user.toString());
		sendingPlasmaEmailtoUsers(user);
		return plasmaDonationService.updateUserRequest(user);
	}
	private void sendingPlasmaEmailtoUsers(PlasmaDonation user) {
		// TODO Auto-generated method stub
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(user.getEmail());
		//if(user.getStatus().equals("1")) {
		if(user.getStatus()=="1") {
			
			msg.setSubject("Plasma Dontion !!!!!!!!!!!!");
			msg.setText(" Dear member,\n Your Plasama Donation has been approve.\n further more details please contact xyz@gmail.com. \n Thanks and Regards,\n Arun Dasari, \n phone no:899845297");
		}
		else {
			msg.setSubject("Plasma Dontion !!!!!!!!!!!!");
			msg.setText(" Dear member,\n Your Plasama Donation did not approve.\n further more details please contact xyz@gmail.com. \n Thanks and Regards,\n Arun Dasari, \n phone no:899845297");
		}


		mailSender.send(msg);
		
	}
	
	private void sendingVaccineEmailtoUsers(VaccineRegistered user) {
		// TODO Auto-generated method stub
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(user.getEmail());
		if(user.getFirstDose().equalsIgnoreCase("1")) {
			
			msg.setSubject("Vaccine Notification !!!!!!!!!!!!");
			msg.setText(" Dear member,\n Your first dose of vaccine has been approve.\n further more details please contact xyz@gmail.com. \n Thanks and Regards,\n Arun Dasari, \n phone no:899845297");
		}
		else if(user.getFirstDose().equalsIgnoreCase("2")) {
			msg.setSubject("Vaccine Notification !!!!!!!!!!!!");
			msg.setText(" Dear member,\n Your first dose of vaccine did not approve.\n further more  details please contact xyz@gmail.com. \n Thanks and Regards,\n Arun Dasari, \n phone no:899845297");
		}
		else if(user.getSecondDose().equalsIgnoreCase("1")) {
			msg.setSubject("Vaccine Notification !!!!!!!!!!!!");
			msg.setText(" Dear member,\n Your second dose of vaccine has been approve.\n further more details please contact xyz@gmail.com. \n Thanks and Regards,\n Arun Dasari, \n phone no:899845297");
			
		}
		else {
			msg.setSubject("Vaccine Notification !!!!!!!!!!!!");
			msg.setText(" Dear member,\n Your second of vaccine did not approve.\n further more details please contact xyz@gmail.com. \n Thanks and Regards,\n Arun Dasari, \n phone no:899845297");
		}


		mailSender.send(msg);
		
	}
	

}
