package com.Payment_Wallet.Service;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Payment_Wallet.Exception.BankAccountException;
import com.Payment_Wallet.Exception.CurrentCustomerSessionException;
import com.Payment_Wallet.Exception.CustomerException;
import com.Payment_Wallet.Exception.TransactionException;
import com.Payment_Wallet.Model.BankAccount;
import com.Payment_Wallet.Model.CurrentCustomerSession;
import com.Payment_Wallet.Model.Customer;
import com.Payment_Wallet.Model.Transaction;
import com.Payment_Wallet.Model.Wallet;
import com.Payment_Wallet.Repositary.BankAccountRepo;
import com.Payment_Wallet.Repositary.CurrentCustomerSessionRepo;
import com.Payment_Wallet.Repositary.CustomerRepo;
import com.Payment_Wallet.Repositary.TransactionRepo;
import com.Payment_Wallet.Repositary.WalletRepo;

@Service
public class WalletServiceImpl implements WalletService{

	@Autowired
	private WalletRepo wRepo;
	
	@Autowired
	private CustomerRepo cRepo;
	
	@Autowired
	private TransactionRepo tRepo;
	
	@Autowired
	private BankAccountRepo bRepo;
	
	@Autowired
	private CurrentCustomerSessionRepo currRepo;

	
	
	
	@Override
	public Customer createAccount(Customer customer) throws CustomerException {
		Optional<Customer> opt = cRepo.findByMobileNumber(customer.getMobileNumber());
		if(opt.isEmpty()) {
			wRepo.save(customer.getWallet());
			return cRepo.save(customer);
		}
		else {
			throw new CustomerException("Customer is already Registered");
		}
	}

	@Override
	public String showBalance(String uuid)
			throws CurrentCustomerSessionException {
		Optional<CurrentCustomerSession> opt = currRepo.findByUuid(uuid);
		if(opt.isPresent()) {
			CurrentCustomerSession curr = opt.get();
			Optional<Customer> cust = cRepo.findById(curr.getId());
			 return "Your current Wallet balance is Rs. "+cust.get().getWallet().getBalance();
		}
		else {
			throw new CurrentCustomerSessionException("Customer is not ");
		}
	}

	@Override
	public String fundTransfer(String targetMobileNumber, BigDecimal amount, String uuid)
			throws CustomerException, TransactionException, CurrentCustomerSessionException {
		Optional<CurrentCustomerSession> opt = currRepo.findByUuid(uuid);
		if(opt.isPresent()) {
			CurrentCustomerSession curr = opt.get();
			Optional<Customer> cust = cRepo.findById(curr.getId());
			Customer givingCustomer = cust.get();
			if(givingCustomer.getWallet().getBalance().compareTo(amount) >=0) {
				Optional<Customer> cust1 = cRepo.findByMobileNumber(targetMobileNumber);
				if(cust1.isPresent()) {
					Customer takingCustomer = cust1.get();
					Transaction fundsTrans = new Transaction();
					Double amt = amount.doubleValue();
					fundsTrans.setAmount(amt);
					fundsTrans.setDescription("Fund Transfer Transaction process");
					fundsTrans.setTransactionDate(LocalDateTime.now());
					fundsTrans.setTransactionType("Cash Transfer");
					
					BigDecimal finalAmt = givingCustomer.getWallet().getBalance().subtract(amount);  
					Wallet givingWallet = givingCustomer.getWallet();
					givingWallet.setBalance(finalAmt);
					givingCustomer.setWallet(givingWallet);
					fundsTrans.setWallet(givingWallet);
					tRepo.save(fundsTrans);
					BigDecimal giveAmt = takingCustomer.getWallet().getBalance().add(amount);
					takingCustomer.getWallet().setBalance(giveAmt);
					cRepo.save(takingCustomer);
					return "Fund Transfer Successful";
					
				}
				else {
					throw new CustomerException("Target Customer is Not Registered");
				}
			}
			else {
				throw new CustomerException("Insufficient Balance to transfer");
			}
		}
		else {
			throw new CurrentCustomerSessionException("Customer is not ");
		}
	}

	@Override
	public String DepositAmounttoBank(Integer accountNo,double amount , String uuid)
			throws BankAccountException, CurrentCustomerSessionException, CustomerException {
		
		Optional<CurrentCustomerSession> opt = currRepo.findByUuid(uuid);
		if(opt.isPresent()) {
			CurrentCustomerSession curr = opt.get();
			Optional<Customer> cust = cRepo.findById(curr.getId());
			Customer givingCustomer = cust.get();
			double givingamt = givingCustomer.getWallet().getBalance().doubleValue();
			if(givingamt >= amount) {
				Optional<BankAccount> ba = bRepo.findById(accountNo);
				if(ba.isPresent()) {
					BankAccount acc = ba.get();
					acc.setBalance(acc.getBalance() + amount);
					BigDecimal minusamt = new BigDecimal(amount);
					BigDecimal onlyminus = givingCustomer.getWallet().getBalance().subtract(minusamt);
					givingCustomer.getWallet().setBalance(onlyminus);
					cRepo.save(givingCustomer);
					acc.setWallet(givingCustomer.getWallet());
					
					Transaction fundsTrans = new Transaction();
					fundsTrans.setAmount(amount);
					fundsTrans.setDescription("Fund Deposit Transaction process");
					fundsTrans.setTransactionDate(LocalDateTime.now());
					fundsTrans.setTransactionType("Bank Transfer");
					fundsTrans.setWallet(givingCustomer.getWallet());
					tRepo.save(fundsTrans);
					
					return "Deposit successfull";
	
				}
				else {
					throw new BankAccountException("Invalid Bank Details");
				}
			}
			else {
				throw new CustomerException("Insufficient Balance to transfer");
			}
		}
		else {
			throw new CurrentCustomerSessionException("Customer not login");
		}
		
	}

	@Override
	public List<Customer> getAllCustomer(String uuid) throws CurrentCustomerSessionException, CustomerException {
		Optional<CurrentCustomerSession> opt = currRepo.findByUuid(uuid);
		if(opt.isPresent()) {
			List<Customer> allCustomers = cRepo.findAll();
			if(allCustomers.isEmpty()) {
				throw new CustomerException("No Customer Has been Registered Yet");
			}
			else {
				return allCustomers;
			}
		}
		else {
			throw new CurrentCustomerSessionException("Customer not login");
		}
	}

	@Override
	public Customer updateAccount(Customer customer, String uuid)
			throws CurrentCustomerSessionException, CustomerException {
		Optional<CurrentCustomerSession> opt = currRepo.findByUuid(uuid);
		if(opt.isPresent()) {
			Optional<Customer> cust = cRepo.findById(opt.get().getId());
			Customer update = cust.get();
			update.setName(customer.getName());
			update.setPassword(customer.getPassword());
			update.setWallet(customer.getWallet());
			return cRepo.save(update);
		}
		else {
			throw new CurrentCustomerSessionException("Customer not login");
		}
	}

	@Override
	public String addMoney(Integer bankId, double amount, String uuid)
			throws BankAccountException, CurrentCustomerSessionException, CustomerException {
		Optional<CurrentCustomerSession> opt = currRepo.findByUuid(uuid);
		if(opt.isPresent()) {
			Optional<Customer> cust = cRepo.findById(opt.get().getId());
			Customer customer = cust.get();
			Optional<BankAccount> ba = bRepo.findById(bankId);
			if(ba.isEmpty()) {
				throw new BankAccountException("Invalid Bank Details");
			}
			else {
				BankAccount acc = ba.get();
				acc.setBalance(acc.getBalance() - amount);
				
				Wallet bawallet = acc.getWallet();
				BigDecimal addamt = new BigDecimal(amount);
				addamt = bawallet.getBalance().add(addamt);
				bawallet.setBalance(addamt);
				acc.setWallet(bawallet);
	
				bRepo.save(acc);
				
				Transaction fundsTrans = new Transaction();
				fundsTrans.setAmount(amount);
				fundsTrans.setDescription("Fund Added to Wallet Transaction process");
				fundsTrans.setTransactionDate(LocalDateTime.now());
				fundsTrans.setTransactionType("Wallet Transfer");
				fundsTrans.setWallet(bawallet);
				tRepo.save(fundsTrans);
				
				return "Money Added successfull";
				
			}
		}
		else {
			throw new CurrentCustomerSessionException("Customer not login");
		}
	}

	

	

}
