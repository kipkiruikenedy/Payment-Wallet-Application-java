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

import com.Payment_Wallet.Exception.BeneficiaryDetailsException;
import com.Payment_Wallet.Exception.CurrentCustomerSessionException;
import com.Payment_Wallet.Exception.CustomerException;
import com.Payment_Wallet.Model.BeneficiaryDetails;
import com.Payment_Wallet.Service.BeneficiaryDetailsService;

@RestController
@RequestMapping("/Payment_Wallet/BeneficiaryDetails")
public class BeneficiaryDetailsController {

	@Autowired
	private BeneficiaryDetailsService bdService;
	
	@PostMapping("/addBeneficiary")
	public ResponseEntity<BeneficiaryDetails> addBeneficiaryDetails(@RequestBody BeneficiaryDetails beneficiaryDetails , @RequestParam("uuid") String uuid) throws CustomerException, BeneficiaryDetailsException, CurrentCustomerSessionException{
		return new ResponseEntity<BeneficiaryDetails>(bdService.addBeneficiaryDetails(beneficiaryDetails, uuid),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<BeneficiaryDetails> deleteBeneficiaryDetails(@RequestParam("beneficiaryDetailsId") Integer beneficiaryDetailsId,@RequestParam("uuid") String uuid) throws BeneficiaryDetailsException, CustomerException, CurrentCustomerSessionException{
		return new ResponseEntity<BeneficiaryDetails>(bdService.deleteBeneficiaryDetails(beneficiaryDetailsId, uuid),HttpStatus.OK);
	}
	
	@GetMapping("/viewBeneficiary")
	public ResponseEntity<BeneficiaryDetails> viewBeneficiary(@RequestParam("mobileNo") String mobileNo,@RequestParam("uuid") String uuid) throws BeneficiaryDetailsException, CustomerException, CurrentCustomerSessionException{
		return new ResponseEntity<BeneficiaryDetails>(bdService.viewBeneficiary(mobileNo, uuid),HttpStatus.OK);		
	}
	
	@GetMapping("/viewAllBeneficiary")
	public ResponseEntity<List<BeneficiaryDetails>> viewAllBeneficiaryDetails(@RequestParam("uuid") String uuid) throws BeneficiaryDetailsException, CustomerException, CurrentCustomerSessionException{
		return new ResponseEntity<List<BeneficiaryDetails>>(bdService.viewAllBeneficiaryDetails(uuid),HttpStatus.OK);
	}
}
