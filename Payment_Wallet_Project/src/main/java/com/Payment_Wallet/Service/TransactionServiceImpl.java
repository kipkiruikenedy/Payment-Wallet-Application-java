package com.Payment_Wallet.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Payment_Wallet.Exception.CurrentCustomerSessionException;
import com.Payment_Wallet.Exception.TransactionException;
import com.Payment_Wallet.Model.CurrentCustomerSession;
import com.Payment_Wallet.Model.Customer;
import com.Payment_Wallet.Model.Transaction;
import com.Payment_Wallet.Model.Wallet;
import com.Payment_Wallet.Repositary.CurrentCustomerSessionRepo;
import com.Payment_Wallet.Repositary.CustomerRepo;



@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private CustomerRepo cRepo;
	
	@Autowired
	private CurrentCustomerSessionRepo currRepo;
	
	@Override
	public List<Transaction> viewAllTransaction(String uuid)
			throws TransactionException, CurrentCustomerSessionException {
		Optional<CurrentCustomerSession> opt = currRepo.findByUuid(uuid);
		if(opt.isPresent()) {
			
			Optional<Customer> cust = cRepo.findById(opt.get().getId());
			Customer customer = cust.get();
			Wallet wallet = customer.getWallet();
			List<Transaction> alltrans = wallet.getAllTrans();
			if(alltrans.isEmpty()) {
				throw new TransactionException("No Transaction has been Done yet");
			}
			else {
				return alltrans;
			}
			
			
		}
		else {
			throw new CurrentCustomerSessionException("Customer is not logged In");
		}
	}

	@Override
	public List<Transaction> viewAllTransactionByType(String type, String uuid)
			throws TransactionException, CurrentCustomerSessionException {
		Optional<CurrentCustomerSession> opt = currRepo.findByUuid(uuid);
		if(opt.isPresent()) {
			
			Optional<Customer> cust = cRepo.findById(opt.get().getId());
			Customer customer = cust.get();
			Wallet wallet = customer.getWallet();
			List<Transaction> alltrans = wallet.getAllTrans();
			
			List<Transaction> filterone = new ArrayList<>();
			for(Transaction t : alltrans) {
				if(t.getTransactionType().equals(type)) {
					filterone.add(t);
				}
			}
			
			if(filterone.isEmpty()) {
				throw new TransactionException("No Transaction has been Done yet");
			}
			else {
				return filterone;
			}
			
			
		}
		else {
			throw new CurrentCustomerSessionException("Customer is not logged In");
		}
	}

}
