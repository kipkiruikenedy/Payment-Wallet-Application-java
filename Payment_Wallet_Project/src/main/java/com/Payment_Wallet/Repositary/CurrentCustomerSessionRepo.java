package com.Payment_Wallet.Repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Payment_Wallet.Model.CurrentCustomerSession;

public interface CurrentCustomerSessionRepo extends JpaRepository<CurrentCustomerSession, Integer>{

	Optional<CurrentCustomerSession> findByUuid(String uuid);
	
}
