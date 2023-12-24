package com.Payment_Wallet.Service;

import java.util.List;

import com.Payment_Wallet.Exception.BeneficiaryDetailsException;
import com.Payment_Wallet.Exception.CurrentCustomerSessionException;
import com.Payment_Wallet.Exception.CustomerException;
import com.Payment_Wallet.Model.BeneficiaryDetails;

public interface BeneficiaryDetailsService {

	BeneficiaryDetails addBeneficiaryDetails(BeneficiaryDetails beneficiaryDetails ,String uuid)throws CustomerException,BeneficiaryDetailsException,CurrentCustomerSessionException;
	
	BeneficiaryDetails deleteBeneficiaryDetails(Integer beneficiaryDetailsId ,String uuid)throws BeneficiaryDetailsException,CustomerException,CurrentCustomerSessionException;
	
	BeneficiaryDetails viewBeneficiary(String mobileNo,String uuid)throws BeneficiaryDetailsException,CustomerException,CurrentCustomerSessionException;
	
	List<BeneficiaryDetails> viewAllBeneficiaryDetails(String uuid)throws BeneficiaryDetailsException,CustomerException,CurrentCustomerSessionException;
	
	
	
}
