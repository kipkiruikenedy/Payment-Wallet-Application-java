package com.Payment_Wallet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Payment_Wallet.Exception.BillPaymentException;
import com.Payment_Wallet.Exception.CurrentCustomerSessionException;
import com.Payment_Wallet.Exception.CustomerException;
import com.Payment_Wallet.Model.BillPayment;
import com.Payment_Wallet.Model.BillPaymentDto;
import com.Payment_Wallet.Service.BillPaymentService;

@RestController
@RequestMapping("/Payment-wallet-application/Bill_Payment")
public class BillPaymentController {
	
	@Autowired
	private BillPaymentService billPaymentService;
	
	@PostMapping("/addBill")
	public ResponseEntity<String> addBill(@RequestBody BillPaymentDto billPaymentDto, @RequestParam("uuid") String uuid) throws BillPaymentException, CurrentCustomerSessionException, CustomerException{
		return new ResponseEntity<String>(billPaymentService.addBillPayment(billPaymentDto, uuid),HttpStatus.OK);
	}
	
	@GetMapping("/viewBill")
	public ResponseEntity<List<BillPayment>> viewBill( @RequestParam("uuid") String uuid) throws BillPaymentException, CurrentCustomerSessionException{
		return new ResponseEntity<List<BillPayment>>(billPaymentService.viewBill(uuid),HttpStatus.OK);
	}
	

}

//public List<BillPayment> viewBill(String uuid)