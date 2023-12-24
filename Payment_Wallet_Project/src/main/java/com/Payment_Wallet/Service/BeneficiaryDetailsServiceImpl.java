package com.Payment_Wallet.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Payment_Wallet.Exception.BeneficiaryDetailsException;
import com.Payment_Wallet.Exception.CurrentCustomerSessionException;
import com.Payment_Wallet.Exception.CustomerException;
import com.Payment_Wallet.Model.BeneficiaryDetails;
import com.Payment_Wallet.Model.CurrentCustomerSession;
import com.Payment_Wallet.Model.Customer;
import com.Payment_Wallet.Model.Wallet;
import com.Payment_Wallet.Repositary.BankAccountRepo;
import com.Payment_Wallet.Repositary.BeneficiaryDetailsRepo;
import com.Payment_Wallet.Repositary.CurrentCustomerSessionRepo;
import com.Payment_Wallet.Repositary.CustomerRepo;
import com.Payment_Wallet.Repositary.TransactionRepo;
import com.Payment_Wallet.Repositary.WalletRepo;

@Service
public class BeneficiaryDetailsServiceImpl implements BeneficiaryDetailsService{

	
	@Autowired
	private CustomerRepo cRepo;
	
	@Autowired
	private CurrentCustomerSessionRepo currRepo;
	
	@Autowired
	private BeneficiaryDetailsRepo bdRepo;
	
	@Override
	public BeneficiaryDetails addBeneficiaryDetails(BeneficiaryDetails beneficiaryDetails, String uuid)
			throws CustomerException, BeneficiaryDetailsException, CurrentCustomerSessionException {
		Optional<CurrentCustomerSession> opt = currRepo.findByUuid(uuid);
		if(opt.isPresent()) {
			Optional<Customer> cus = cRepo.findById(opt.get().getId());
			Customer customer = cus.get();
			Wallet wallet = customer.getWallet();
			List<BeneficiaryDetails> allBeneficiaryDetails = wallet.getAllBeneficiaryDetails();	
		    
			if(allBeneficiaryDetails.isEmpty()) {
				allBeneficiaryDetails.add(beneficiaryDetails);
				wallet.setAllBeneficiaryDetails(allBeneficiaryDetails);
				customer.setWallet(wallet);
				cRepo.save(customer);
				beneficiaryDetails.setWallet(wallet);
				
				return bdRepo.save(beneficiaryDetails);
				
				
			}
			else {
				String mobileno = beneficiaryDetails.getMobileNumber();
				boolean checking = check(allBeneficiaryDetails,mobileno);
				if(checking==true) {
					
					allBeneficiaryDetails.add(beneficiaryDetails);
					wallet.setAllBeneficiaryDetails(allBeneficiaryDetails);
					customer.setWallet(wallet);
					cRepo.save(customer);
					beneficiaryDetails.setWallet(wallet);
					
					return bdRepo.save(beneficiaryDetails);
					
				}
				else {
					throw new BeneficiaryDetailsException("This Benificiary Details is already given");
				}
				
				
			}
			
		}
		else {
			throw new CurrentCustomerSessionException("Customer is not login");
		}
	}

	private boolean check(List<BeneficiaryDetails> allBeneficiaryDetails, String mobileno) {
		
		for(BeneficiaryDetails bd : allBeneficiaryDetails) {
			if(bd.equals(mobileno)) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public BeneficiaryDetails deleteBeneficiaryDetails(Integer beneficiaryDetailsId, String uuid)
			throws BeneficiaryDetailsException, CustomerException, CurrentCustomerSessionException {
		Optional<CurrentCustomerSession> opt = currRepo.findByUuid(uuid);
		if(opt.isPresent()) {
			Optional<Customer> cus = cRepo.findById(opt.get().getId());
			Customer customer = cus.get();
			Wallet wallet = customer.getWallet();
			List<BeneficiaryDetails> allBeneficiaryDetails = wallet.getAllBeneficiaryDetails();	
		    
			Optional<BeneficiaryDetails> bendet = bdRepo.findById(beneficiaryDetailsId);
			if(bendet.isPresent()) {
				BeneficiaryDetails ben = bendet.get();
				allBeneficiaryDetails.remove(ben);
				wallet.setAllBeneficiaryDetails(allBeneficiaryDetails);
				customer.setWallet(wallet);
				cRepo.save(customer);
				
				bdRepo.delete(ben);
				
				return ben;
			}
			else {
				throw new BeneficiaryDetailsException("No beneficairy Details found with the detials");
			}		
		}
		else {
			throw new CurrentCustomerSessionException("Customer is not login");
		}
	}

	@Override
	public BeneficiaryDetails viewBeneficiary(String mobileNo, String uuid)
			throws BeneficiaryDetailsException, CustomerException, CurrentCustomerSessionException {
		Optional<CurrentCustomerSession> opt = currRepo.findByUuid(uuid);
		if(opt.isPresent()) {
			
			Optional<BeneficiaryDetails> ben = bdRepo.findByMobileNumber(mobileNo);
			if(ben.isPresent()) {
				
				return ben.get();
				
			}
			else {
				throw new BeneficiaryDetailsException("No beneficiary found with the details");
			}
		}
		else {
			throw new CurrentCustomerSessionException("User is not Logged In");
		}
	}

	@Override
	public List<BeneficiaryDetails> viewAllBeneficiaryDetails(String uuid)
			throws BeneficiaryDetailsException, CustomerException, CurrentCustomerSessionException {
		Optional<CurrentCustomerSession> opt = currRepo.findByUuid(uuid);
		if(opt.isPresent()) {
			Optional<Customer> cus = cRepo.findById(opt.get().getId());
			Customer customer = cus.get();
			Wallet wallet = customer.getWallet();
			List<BeneficiaryDetails> allBeneficiaryDetails = wallet.getAllBeneficiaryDetails();	
			if(allBeneficiaryDetails.isEmpty()) {
				
				throw new BeneficiaryDetailsException("No beneficiary found with the data");
				
			}
			else {
				return allBeneficiaryDetails;
			}
		}
		else {
			throw new CurrentCustomerSessionException("User is not Logged In");
		}
	}

}
