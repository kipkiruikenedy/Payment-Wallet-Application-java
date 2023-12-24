package com.Payment_Wallet.Service;

import java.util.List;

import com.Payment_Wallet.Exception.BankAccountException;
import com.Payment_Wallet.Exception.CurrentCustomerSessionException;
import com.Payment_Wallet.Exception.WalletException;
import com.Payment_Wallet.Model.BankAccount;
import com.Payment_Wallet.Model.Wallet;

public interface BankAccountService {

	BankAccount addAccount(BankAccount bacc,String uuid)throws CurrentCustomerSessionException,WalletException,BankAccountException;
	
	BankAccount removeAccount(String accNo, String uuid)throws CurrentCustomerSessionException,WalletException,BankAccountException;
	
	BankAccount viewAcc(String accNo,String uuid)throws CurrentCustomerSessionException,BankAccountException,WalletException;
	
	List<BankAccount> viewAllAcc(String uuid)throws CurrentCustomerSessionException,WalletException;
}
