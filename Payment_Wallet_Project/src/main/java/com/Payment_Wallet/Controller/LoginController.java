package com.Payment_Wallet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Payment_Wallet.Exception.CurrentCustomerSessionException;
import com.Payment_Wallet.Exception.CustomerException;
import com.Payment_Wallet.Model.CurrentCustomerSession;
import com.Payment_Wallet.Model.LoginDTO;
import com.Payment_Wallet.Service.LoginService;

@RestController
@RequestMapping("/Payment-wallet-application")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	
	@PostMapping("/Login")
	public ResponseEntity<CurrentCustomerSession> loginHandler(@RequestBody LoginDTO loginDTO) throws CurrentCustomerSessionException, CustomerException{
		return new ResponseEntity<CurrentCustomerSession>(loginService.login(loginDTO),HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logoutHandler(@RequestParam("uuid") String uuid) throws CurrentCustomerSessionException{
		return new ResponseEntity<String>(loginService.logout(uuid),HttpStatus.OK);
	}
}
