package com.Payment_Wallet.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Payment_Wallet.Model.Wallet;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Integer>{

}
