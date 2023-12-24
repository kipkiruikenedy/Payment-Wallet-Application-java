package com.Payment_Wallet.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Payment_Wallet.Model.BillPayment;

@Repository
public interface BillPaymentRepo extends JpaRepository<BillPayment, String>{

}
