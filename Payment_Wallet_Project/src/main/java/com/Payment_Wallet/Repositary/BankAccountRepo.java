package com.Payment_Wallet.Repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Payment_Wallet.Model.BankAccount;

@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount, Integer>{

	Optional<BankAccount> findByAccountNo(String accountNo);
	
}
