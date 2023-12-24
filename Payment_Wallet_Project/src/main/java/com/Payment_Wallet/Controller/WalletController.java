package com.Payment_Wallet.Controller;



import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Payment_Wallet.Exception.BankAccountException;
import com.Payment_Wallet.Exception.CurrentCustomerSessionException;
import com.Payment_Wallet.Exception.CustomerException;
import com.Payment_Wallet.Exception.TransactionException;
import com.Payment_Wallet.Exception.WalletException;
import com.Payment_Wallet.Model.Customer;
import com.Payment_Wallet.Service.WalletService;

@RestController
@RequestMapping("/Payment_Wallet/Wallet")
public class WalletController {

	@Autowired
	private WalletService wService;

	@PostMapping("/register")
	public ResponseEntity<Customer> registerHandler(@RequestBody  Customer customer)  throws CustomerException, WalletException{
		return new ResponseEntity<Customer>(wService.createAccount(customer),HttpStatus.CREATED);
	}
	
	@GetMapping("/showBalance")
	public ResponseEntity<String> getBalance(@RequestParam("uuid") String uuid) throws CustomerException, CurrentCustomerSessionException{
		return new ResponseEntity<String>(wService.showBalance(uuid),HttpStatus.OK);
	}
	
	@GetMapping("/fundTransfer")
	public ResponseEntity<String> fundTransfer(@RequestParam("targetMobileNumber") String targetMobileNumber, @RequestParam("amount") BigDecimal amount, @RequestParam("uuid") String uuid) throws CustomerException, TransactionException, CurrentCustomerSessionException{
		return new ResponseEntity<String>(wService.fundTransfer(targetMobileNumber, amount, uuid),HttpStatus.OK);
	}
	
	@GetMapping("/DeposittoBank")
	public ResponseEntity<String> DepositAmounttoBank(@RequestParam("accountNo") Integer accountNo, @RequestParam("amount") double amount, @RequestParam("uuid") String uuid) throws BankAccountException, CurrentCustomerSessionException, CustomerException{
		return new ResponseEntity<String>(wService.DepositAmounttoBank(accountNo, amount, uuid),HttpStatus.OK);
	}
	
	@GetMapping("/allCustomer")
	public ResponseEntity<List<Customer>> getAllCustomer(@RequestParam("uuid") String uuid) throws CurrentCustomerSessionException, CustomerException{
		return new ResponseEntity<List<Customer>>(wService.getAllCustomer(uuid),HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Customer> updateAcc(@RequestBody Customer customer, @RequestParam("uuid") String uuid) throws CurrentCustomerSessionException, CustomerException{
		return new ResponseEntity<Customer>(wService.updateAccount(customer, uuid),HttpStatus.OK);
	}
	
	@GetMapping("/addMoney")
	public ResponseEntity<String> addMoney(@RequestParam("bankId") Integer bankId, @RequestParam("amount") double amount, @RequestParam("uuid") String uuid) throws BankAccountException, CurrentCustomerSessionException, CustomerException{
		return new ResponseEntity<String>(wService.addMoney(bankId, amount, uuid),HttpStatus.OK);
	}
	
}
