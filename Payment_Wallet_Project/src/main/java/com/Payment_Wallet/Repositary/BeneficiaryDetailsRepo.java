package com.Payment_Wallet.Repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Payment_Wallet.Model.BeneficiaryDetails;

@Repository
public interface BeneficiaryDetailsRepo extends JpaRepository<BeneficiaryDetails, Integer>{

	Optional<BeneficiaryDetails> findByMobileNumber(String mobileNumber);
}
