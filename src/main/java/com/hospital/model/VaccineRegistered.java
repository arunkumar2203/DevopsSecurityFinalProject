package com.hospital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vaccine_registration")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VaccineRegistered {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String age;
	@Column
	private String description;
	@Column
	private String firstDose;
	@Column
	private String secondDose;
	@Column
	private String phoneNumber;
	@Column
	private String governmentId;
	@Column
	private String address;
	@Column
	private String state;

}
