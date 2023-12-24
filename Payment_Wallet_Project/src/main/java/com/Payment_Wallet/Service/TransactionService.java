package com.Payment_Wallet.Service;

import java.util.List;

import com.Payment_Wallet.Exception.CurrentCustomerSessionException;
import com.Payment_Wallet.Exception.TransactionException;
import com.Payment_Wallet.Model.Transaction;

import jakarta.transaction.TransactionalException;

public interface TransactionService {

	List<Transaction> viewAllTransaction(String uuid)throws TransactionException,CurrentCustomerSessionException; 
	
	List<Transaction> viewAllTransactionByType(String type ,String uuid)throws TransactionException,CurrentCustomerSessionException; 
	
	
}
