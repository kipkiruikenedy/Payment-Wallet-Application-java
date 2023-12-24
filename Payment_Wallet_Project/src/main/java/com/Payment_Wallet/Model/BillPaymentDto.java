package com.Payment_Wallet.Model;

import java.time.LocalDateTime;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BillPaymentDto {


	private String billType;
	private Double amount;
	private LocalDateTime paymentDate;
	
}
