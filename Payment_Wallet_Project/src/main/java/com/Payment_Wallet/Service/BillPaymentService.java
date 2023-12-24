package com.Payment_Wallet.Service;

import java.util.List;

import com.Payment_Wallet.Exception.BillPaymentException;
import com.Payment_Wallet.Exception.CurrentCustomerSessionException;
import com.Payment_Wallet.Exception.CustomerException;
import com.Payment_Wallet.Model.BillPayment;
import com.Payment_Wallet.Model.BillPaymentDto;

public interface BillPaymentService {

	String addBillPayment(BillPaymentDto billPaymentDto,String uuid)throws BillPaymentException,CurrentCustomerSessionException,CustomerException;

	List<BillPayment> viewBill(String uuid)throws BillPaymentException,CurrentCustomerSessionException;
}
