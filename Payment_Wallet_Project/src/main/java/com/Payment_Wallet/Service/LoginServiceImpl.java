package com.Payment_Wallet.Service;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Payment_Wallet.Exception.CurrentCustomerSessionException;
import com.Payment_Wallet.Exception.CustomerException;
import com.Payment_Wallet.Model.CurrentCustomerSession;
import com.Payment_Wallet.Model.Customer;
import com.Payment_Wallet.Model.LoginDTO;
import com.Payment_Wallet.Repositary.CurrentCustomerSessionRepo;
import com.Payment_Wallet.Repositary.CustomerRepo;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private CurrentCustomerSessionRepo currRepo;
	
	@Autowired
	private CustomerRepo cRepo;
	
	@Override
	public CurrentCustomerSession login(LoginDTO loginDTO) throws CurrentCustomerSessionException,CustomerException {
		Optional<Customer> validUser = cRepo.findByMobileNumber(loginDTO.getMobileNumber());
		if(validUser.isPresent()) {
			Customer cust = validUser.get();
			Optional<CurrentCustomerSession> currUser = currRepo.findById(cust.getCustomerId());
			if(currUser.isPresent()) {
				throw new CurrentCustomerSessionException("Customer already logged in");
			}
			else {
				String key =RandomStringUtils.randomAlphanumeric(6);;
						
				if(loginDTO.getPassword().equals(cust.getPassword())) {
					CurrentCustomerSession curr = new CurrentCustomerSession();
					curr.setId(cust.getCustomerId());
					curr.setUuid(key);
					return currRepo.save(curr);
				}
				else {
					throw new CustomerException("Enter a valid password");
				}
			}
		}
		else {
			throw new CustomerException("Customer is not Registered");
		}
	}

	@Override
	public String logout(String uuid) throws CurrentCustomerSessionException {
		Optional<CurrentCustomerSession> currentUser = currRepo.findByUuid(uuid);
		if(currentUser.isPresent()) {
			currRepo.delete(currentUser.get());
			return "Logout Successfull";
		}
		else {
			throw new CurrentCustomerSessionException("User is not login");
		}
	}
	

}
