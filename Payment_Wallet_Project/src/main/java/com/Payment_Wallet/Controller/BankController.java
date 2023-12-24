package com.Payment_Wallet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Payment_Wallet.Exception.BankAccountException;
import com.Payment_Wallet.Exception.CurrentCustomerSessionException;
import com.Payment_Wallet.Exception.WalletException;
import com.Payment_Wallet.Model.BankAccount;
import com.Payment_Wallet.Model.Wallet;
import com.Payment_Wallet.Service.BankAccountService;

import lombok.Delegate;

@RestController
@RequestMapping("/Payment_Wallet/Bank")
public class BankController {

	@Autowired
	private BankAccountService bankAccountService;
	
	
	@PostMapping("/addBank")
	public ResponseEntity<BankAccount> addBank(@RequestBody BankAccount bacc, @RequestParam("uuid") String uuid) throws CurrentCustomerSessionException, WalletException, BankAccountException{
		return new ResponseEntity<BankAccount>(bankAccountService.addAccount(bacc, uuid),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<BankAccount> deleteBank(@RequestParam("accNo") String accNo, @RequestParam("uuid") String uuid) throws CurrentCustomerSessionException, WalletException, BankAccountException{
		return new ResponseEntity<BankAccount>(bankAccountService.removeAccount(accNo, uuid),HttpStatus.OK);
	}
	
	@GetMapping("/viewAcc")
	public ResponseEntity<BankAccount> viewAcc(@RequestParam("accNo") String accNo, @RequestParam("uuid") String uuid) throws CurrentCustomerSessionException, BankAccountException, WalletException{
		return new ResponseEntity<BankAccount>(bankAccountService.viewAcc(accNo, uuid),HttpStatus.OK);
	}
	
	@GetMapping("/viewAllAcc")
	public ResponseEntity<List<BankAccount>> viewAllAcc( @RequestParam("uuid") String uuid) throws CurrentCustomerSessionException, WalletException{
		return new ResponseEntity<List<BankAccount>>(bankAccountService.viewAllAcc(uuid),HttpStatus.OK);
	}
}