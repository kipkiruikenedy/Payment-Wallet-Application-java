package com.Payment_Wallet.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BeneficiaryDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer benId;
	private String name;
	private String mobileNumber;

	@ManyToOne
	@JsonIgnore
	private Wallet wallet;
}
