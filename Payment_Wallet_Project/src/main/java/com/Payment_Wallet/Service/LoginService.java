package com.Payment_Wallet.Service;

import com.Payment_Wallet.Exception.CurrentCustomerSessionException;
import com.Payment_Wallet.Exception.CustomerException;
import com.Payment_Wallet.Model.CurrentCustomerSession;
import com.Payment_Wallet.Model.LoginDTO;

public interface LoginService {

	CurrentCustomerSession login(LoginDTO loginDTO)throws CurrentCustomerSessionException, CustomerException;

	String logout(String uuid)throws CurrentCustomerSessionException;
	
}
