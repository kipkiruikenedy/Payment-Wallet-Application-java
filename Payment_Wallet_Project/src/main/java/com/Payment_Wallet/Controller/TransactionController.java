package com.Payment_Wallet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Payment_Wallet.Exception.CurrentCustomerSessionException;
import com.Payment_Wallet.Exception.TransactionException;
import com.Payment_Wallet.Model.Transaction;
import com.Payment_Wallet.Service.TransactionService;


@RestController
@RequestMapping("/Payment_Wallet/Transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/viewAllTransaction")
	public ResponseEntity<List<Transaction>> viewAllTransaction(@RequestParam("uuid") String uuid) throws TransactionException, CurrentCustomerSessionException{
		return new ResponseEntity<List<Transaction>>(transactionService.viewAllTransaction(uuid),HttpStatus.OK);
	}
	
	@GetMapping("/viewAllTransactionByType")
	public ResponseEntity<List<Transaction>> viewAllTransactionByType(@RequestParam("type") String type,@RequestParam("uuid") String uuid) throws TransactionException, CurrentCustomerSessionException{
		return new ResponseEntity<List<Transaction>>(transactionService.viewAllTransactionByType(type, uuid),HttpStatus.OK);
		}
	
}
