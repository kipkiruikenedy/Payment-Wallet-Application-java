package com.Payment_Wallet.Service;


import java.math.BigDecimal;
import java.util.List;

import com.Payment_Wallet.Exception.BankAccountException;
import com.Payment_Wallet.Exception.CurrentCustomerSessionException;
import com.Payment_Wallet.Exception.CustomerException;
import com.Payment_Wallet.Exception.TransactionException;
import com.Payment_Wallet.Model.Customer;

public interface WalletService {

	Customer createAccount(Customer customer)throws CustomerException;
	
	String showBalance(String uuid)throws CustomerException,CurrentCustomerSessionException;
	
	String fundTransfer(String targetMobileNumber, BigDecimal amount,String uuid)throws CustomerException,TransactionException, CurrentCustomerSessionException;
	
	String DepositAmounttoBank(Integer accountNo,double amount , String uuid)throws BankAccountException,CurrentCustomerSessionException,CustomerException ;
	
	List<Customer> getAllCustomer(String uuid)throws CurrentCustomerSessionException,CustomerException ;
	
	Customer updateAccount(Customer customer, String uuid)throws CurrentCustomerSessionException,CustomerException ;
	
	String addMoney(Integer bankId,double amount , String uuid)throws BankAccountException,CurrentCustomerSessionException,CustomerException ;
}
