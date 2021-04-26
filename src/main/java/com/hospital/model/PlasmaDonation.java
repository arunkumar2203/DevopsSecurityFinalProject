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
import lombok.ToString;

@Entity
@Table(name = "plasma_donations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class PlasmaDonation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String username;
	@Column
	private String bloodGroup;
	@Column
	private float units;
	@Column
	private String disease;
	@Column
	private String age;
	@Column
	private String status;
	@Column
	private String email;

}
