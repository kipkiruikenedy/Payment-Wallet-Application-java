package com.Payment_Wallet.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Payment_Wallet.Exception.BankAccountException;
import com.Payment_Wallet.Exception.CurrentCustomerSessionException;
import com.Payment_Wallet.Exception.WalletException;
import com.Payment_Wallet.Model.BankAccount;
import com.Payment_Wallet.Model.CurrentCustomerSession;
import com.Payment_Wallet.Model.Customer;
import com.Payment_Wallet.Model.Wallet;
import com.Payment_Wallet.Repositary.BankAccountRepo;
import com.Payment_Wallet.Repositary.CurrentCustomerSessionRepo;
import com.Payment_Wallet.Repositary.CustomerRepo;
import com.Payment_Wallet.Repositary.WalletRepo;

@Service
public class BankAccountServiceImpl implements BankAccountService{

	@Autowired
	private WalletRepo wRepo;
	
	@Autowired
	private CustomerRepo cRepo;
		
	@Autowired
	private BankAccountRepo bRepo;
	
	@Autowired
	private CurrentCustomerSessionRepo currRepo;
	
	
	@Override
	public BankAccount addAccount(BankAccount bacc, String uuid)
			throws CurrentCustomerSessionException, WalletException, BankAccountException {
		
		Optional<CurrentCustomerSession> opt =  currRepo.findByUuid(uuid);
		if(opt.isPresent()) {
			Optional<Customer> cust = cRepo.findById(opt.get().getId());
			Customer customer = cust.get();
			Wallet wallet = customer.getWallet();
			List<BankAccount> allBank = wallet.getBankAccount();
			if(allBank.isEmpty()) {
				bacc.setWallet(wallet);
				bRepo.save(bacc);
				allBank.add(bacc);
				wallet.setBankAccount(allBank);
				
				wRepo.save(wallet);
				return bRepo.save(bacc);
			}
			else {
				String accNo = bacc.getAccountNo();
				boolean acchave = checkbankExist(allBank,accNo);
				if(acchave==true) {
					
					bacc.setWallet(wallet);
					bRepo.save(bacc);
					allBank.add(bacc);
					wallet.setBankAccount(allBank);
					wRepo.save(wallet);
					customer.setWallet(wallet);
					cRepo.save(customer);
					
					return bRepo.save(bacc);
					
				}
				else {
					throw new BankAccountException("Bank is already added to the wallet");
				}
			}
		}
		else {
			throw new CurrentCustomerSessionException("Customer is not logged in");
		}
		
	}


	private boolean checkbankExist(List<BankAccount> allBank, String accNo) {
		
		for(BankAccount ba : allBank) {
			if(ba.getAccountNo().equals(accNo)) {
				return false;
			}
		}
		return true;
		
	}


	@Override
	public BankAccount removeAccount(String accNo, String uuid)
			throws CurrentCustomerSessionException, WalletException, BankAccountException {
		Optional<CurrentCustomerSession> opt = currRepo.findByUuid(uuid);
		if(opt.isPresent()) {
			Optional<Customer> cust = cRepo.findById(opt.get().getId());
			Customer customer = cust.get();
			Wallet wallet = customer.getWallet();
			List<BankAccount> allBank = wallet.getBankAccount();
			if(allBank.isEmpty()) {
				throw new BankAccountException("This Customer has not linked any bank ");
			}
			else {
				boolean acchave = checkbankExist(allBank,accNo);
				if(acchave==false) {
					
					Optional<BankAccount> ba = bRepo.findByAccountNo(accNo);
					BankAccount bacc = ba.get();
					allBank.remove(bacc);
					wallet.setBankAccount(allBank);
					wRepo.save(wallet);
					customer.setWallet(wallet);
					cRepo.save(customer);
					
					bRepo.delete(bacc);
					return bacc;
					
					
					
				}
				else {
					throw new WalletException("Wallet doesnot have this bank account");
				}
			}
		}
		else {
			throw new CurrentCustomerSessionException("Customer is not logged in");
		}
	}


	@Override
	public BankAccount viewAcc(String accNo, String uuid) throws CurrentCustomerSessionException, BankAccountException, WalletException {
		Optional<CurrentCustomerSession> opt = currRepo.findByUuid(uuid);
		if(opt.isPresent()) {
			Optional<Customer> cust = cRepo.findById(opt.get().getId());
			Customer customer = cust.get();
			Wallet wallet = customer.getWallet();
			List<BankAccount> allBank = wallet.getBankAccount();
			if(allBank.isEmpty()) {
				throw new BankAccountException("This Customer has not linked any bank ");
			}
			else {
				boolean acchave = checkbankExist(allBank,accNo);
				if(acchave==false) {
					
					Optional<BankAccount> ba = bRepo.findByAccountNo(accNo);
					return ba.get();
				}
				else {
					throw new WalletException("Wallet doesnot have this bank account");
				}
			}
		}
		else {
			throw new CurrentCustomerSessionException("Customer is not logged in");
		}
		
	}


	@Override
	public List<BankAccount> viewAllAcc(String uuid) throws CurrentCustomerSessionException,WalletException {
		Optional<CurrentCustomerSession> opt = currRepo.findByUuid(uuid);
		if(opt.isPresent()) {
			Optional<Customer> cust = cRepo.findById(opt.get().getId());
			Customer customer = cust.get();
			Wallet wallet = customer.getWallet();
			List<BankAccount> allBank = wallet.getBankAccount();
			if(allBank.isEmpty()) {
				throw new WalletException("This Customer has not linked any bank ");
			}
			else {
				return allBank;
			}
		}
		else {
			throw new CurrentCustomerSessionException("Customer is not logged in");
		}
	}



}
