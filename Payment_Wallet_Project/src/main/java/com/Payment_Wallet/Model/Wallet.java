package com.Payment_Wallet.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer walletId;
	
	
	private BigDecimal balance;
	
	@OneToOne(mappedBy = "wallet")
	@JsonIgnore
	private Customer customer;
	
	@OneToMany(mappedBy = "wallet")
	@JsonIgnore
	private List<BankAccount> bankAccount;
	
	@OneToMany(mappedBy = "wallet")
	@JsonIgnore
    private List<BeneficiaryDetails> allBeneficiaryDetails = new ArrayList<>();
    
	@OneToMany(mappedBy = "wallet")
	@JsonIgnore
    private List<BillPayment> allBillPayment = new ArrayList<>();
    
	@OneToMany(mappedBy = "wallet")
	@JsonIgnore
    private List<Transaction> allTrans = new ArrayList<>();
    
	
	
	
	
}
