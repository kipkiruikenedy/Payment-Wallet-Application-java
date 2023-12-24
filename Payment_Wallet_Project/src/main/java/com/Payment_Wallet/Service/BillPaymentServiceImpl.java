package com.Payment_Wallet.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Payment_Wallet.Exception.BillPaymentException;
import com.Payment_Wallet.Exception.CurrentCustomerSessionException;
import com.Payment_Wallet.Exception.CustomerException;
import com.Payment_Wallet.Model.BankAccount;
import com.Payment_Wallet.Model.BillPayment;
import com.Payment_Wallet.Model.BillPaymentDto;
import com.Payment_Wallet.Model.CurrentCustomerSession;
import com.Payment_Wallet.Model.Customer;
import com.Payment_Wallet.Model.Transaction;
import com.Payment_Wallet.Model.Wallet;
import com.Payment_Wallet.Repositary.BankAccountRepo;
import com.Payment_Wallet.Repositary.BillPaymentRepo;
import com.Payment_Wallet.Repositary.CurrentCustomerSessionRepo;
import com.Payment_Wallet.Repositary.CustomerRepo;
import com.Payment_Wallet.Repositary.TransactionRepo;
import com.Payment_Wallet.Repositary.WalletRepo;

@Service
public class BillPaymentServiceImpl implements BillPaymentService{

	
	@Autowired
	private CustomerRepo cRepo;
	
	@Autowired
	private CurrentCustomerSessionRepo currRepo;
	
	@Autowired
	private BillPaymentRepo billRepo;
	
	@Autowired
	private TransactionRepo tRepo;
	
	@Override
	public String addBillPayment(BillPaymentDto billPaymentDto, String uuid)
			throws BillPaymentException, CurrentCustomerSessionException, CustomerException {
		Optional<CurrentCustomerSession> opt = currRepo.findByUuid(uuid);
		if(opt.isPresent()) {
		    Optional<Customer> cust = cRepo.findById(opt.get().getId());
		    Customer givingCustomer  = cust.get();
		    Wallet wallet = givingCustomer.getWallet();
		    BigDecimal amt = new BigDecimal(billPaymentDto.getAmount());
		    if(givingCustomer.getWallet().getBalance().compareTo(amt) >=0) {
		    	
		    	BigDecimal sub = wallet.getBalance().subtract(amt);
		    	double subamt = sub.doubleValue();
		    	
		    	List<BillPayment> allbill = wallet.getAllBillPayment();
		    	if(allbill.isEmpty()) {
		    		BillPayment bill = new BillPayment();
			    	String bid = RandomStringUtils.randomAlphanumeric(3);
			    	bill.setBillId(bid);
			    	bill.setAmount(billPaymentDto.getAmount());
			    	bill.setBillType(billPaymentDto.getBillType());
			    	bill.setPaymentDate(LocalDateTime.now());
			    	wallet.setBalance(sub);
			    	bill.setWallet(wallet);
			    	allbill.add(bill);
			    	billRepo.save(bill);
			    	
			    	Transaction transaction = new Transaction();
			    	
			    	transaction.setAmount(billPaymentDto.getAmount());
			    	transaction.setDescription("Bill Payment Transaction Process");
			    	transaction.setTransactionDate(LocalDateTime.now());
			    	transaction.setTransactionType("Bill Paying");
			    	
			    	transaction.setWallet(wallet);
			    	tRepo.save(transaction);
			    	
			    	return "Bill Payment Successfull";
			    	
			    	
			    	
		    	}
		    	else {
		    		double bamt = billPaymentDto.getAmount();
			        LocalDateTime bdate = billPaymentDto.getPaymentDate();
			        String bType= billPaymentDto.getBillType(); 
			    	boolean check = checkexistBill(allbill, bamt, bdate, bType); 
			    	if(check==true) {
			    		throw new BillPaymentException("This Bill is already added");
			    	}
			    	else {
			    		BillPayment bill1 = new BillPayment();
				    	String bid = RandomStringUtils.randomAlphanumeric(3);
				    	bill1.setBillId(bid);
				    	bill1.setAmount(billPaymentDto.getAmount());
				    	bill1.setBillType(billPaymentDto.getBillType());
				    	bill1.setPaymentDate(LocalDateTime.now());
				    	wallet.setBalance(sub);
				    	bill1.setWallet(wallet);
				    	allbill.add(bill1);
				    	billRepo.save(bill1);
				    	
				    	Transaction transaction1 = new Transaction();
				    	
				    	transaction1.setAmount(billPaymentDto.getAmount());
				    	transaction1.setDescription("Bill Payment Transaction Process");
				    	transaction1.setTransactionDate(LocalDateTime.now());
				    	transaction1.setTransactionType("Bill Paying");
				    	
				    	transaction1.setWallet(wallet);
				    	tRepo.save(transaction1);
				    	
				    	return "Bill Payment Successfull";
			    	}
		    	}
		    	
		    	
		    }
		    else {
				throw new CustomerException("Insufficient Balance to transfer");
			}
		}
		else {
			throw new CurrentCustomerSessionException("Customer is not logged In");
		}
	}
	
	private boolean checkexistBill(List<BillPayment> allbill, double bamt, LocalDateTime bdate,String bType) {
		
		for(BillPayment b : allbill) {
			if (b.getBillType().equals(bType) && b.getAmount()==bamt && b.getPaymentDate().equals(bdate)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<BillPayment> viewBill(String uuid) throws BillPaymentException, CurrentCustomerSessionException {
		Optional<CurrentCustomerSession> opt = currRepo.findByUuid(uuid);
		if(opt.isPresent()) {
			Optional<Customer> cust = cRepo.findById(opt.get().getId());
		    Customer givingCustomer  = cust.get();
		    Wallet wallet = givingCustomer.getWallet();
		    List<BillPayment> allBill = wallet.getAllBillPayment();
		    if(allBill.isEmpty()) {
		    	throw new BillPaymentException("No Bill Added Yet");
		    }
		    else {
		    	return allBill;
		    }
		}
		else {
			throw new CurrentCustomerSessionException("Customer is not logged In");
		}
	}	
}


