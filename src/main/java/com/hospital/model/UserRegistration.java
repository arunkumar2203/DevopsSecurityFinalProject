package com.hospital.model;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRegistration {

	private String username;
	private String password;
	private String email;
	private String userType;
	private String address;
	private String age;
	private String description;
	private String firstDose;
	private String secondDose;
	private String governmentId;
	private String phone;
	private String state;

}
