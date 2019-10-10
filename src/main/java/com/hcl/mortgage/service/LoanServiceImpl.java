package com.hcl.mortgage.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.hcl.mortgage.dto.LoanRequestDto;
import com.hcl.mortgage.entity.Customer;
import com.hcl.mortgage.entity.LoanDetails;
import com.hcl.mortgage.exception.CommonException;
import com.hcl.mortgage.repository.CustomerRepository;
import com.hcl.mortgage.repository.LoanRepository;
import com.hcl.mortgage.util.Email;
import com.hcl.mortgage.util.ExceptionConstants;
import com.hcl.mortgage.util.Sms;


/**
 * @author Subashri Sridharan
 *
 */
@Service
public class LoanServiceImpl implements LoanService{

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	LoanRepository loanRepository;
	@Autowired
	JavaMailSender javaMailSender;
	Sms sms=new Sms();
	Email email=new Email();
	
	
	
	/**
	 * @author Return Customer Loan Summary
	 * It takes customerId as an argument
	 */

	
	@Override
	public LoanDetails getLoanSummary(int customerId) {
		LoanDetails loanInfo;
		Optional<Customer> customer=customerRepository.findByCustomerId(customerId);
		if(!customer.isPresent()) {	
			throw new CommonException(ExceptionConstants.ACCOUNT_NOT_FOUND);
		}
		else {
			loanInfo=loanRepository.findByCustomerId(customerId);
		}
		if(loanInfo==null) {
			throw new CommonException(ExceptionConstants.LOANINFO_UNAVAILABLE);
		}
		return loanInfo;
	}

	@Override
	public String applyLoan(LoanRequestDto loanRequestDto) {
		LoanDetails loanDetail=new LoanDetails();
		BeanUtils.copyProperties(loanRequestDto, loanDetail);
		Customer customer=null;
		Optional<Customer> customerInfo=customerRepository.findByCustomerId(loanRequestDto.getCustomerId());
		if(customerInfo.isPresent()) {
			customer=customerInfo.get();
		}
		LoanDetails loanData=loanRepository.findByCustomerId(customer.getCustomerId());
		if(loanData!=null) {
			throw new CommonException(ExceptionConstants.LOAN_NOT_APPLICABLE);
		}
		long accountNumber = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		loanDetail.setLoanAccountNumber(accountNumber);
		loanDetail.setRateOfInterest(4F);
		sms.sendSms(customer.getMobileNumber(),accountNumber,customer.getPassword(),"Loan");
		email.sendEmail(customer.getEmailId(),accountNumber,customer.getPassword(),javaMailSender,"Loan");
		loanRepository.save(loanDetail);
		return "Loan Applied Successfully";
	}

	
}
