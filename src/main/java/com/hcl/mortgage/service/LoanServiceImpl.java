package com.hcl.mortgage.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.mortgage.dto.LoanInfoRequestDto;
import com.hcl.mortgage.dto.LoanInfoResponseDto;
import com.hcl.mortgage.dto.LoginResponseDto;
import com.hcl.mortgage.entity.Customer;
import com.hcl.mortgage.exception.CommonException;
import com.hcl.mortgage.repository.AccountRepository;
import com.hcl.mortgage.repository.CustomerRepository;
import com.hcl.mortgage.util.ExceptionConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoanServiceImpl implements LoanService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AccountRepository accountRepository;
	

	/*
	 * This method is used to get the loan information for customer by providing valid credentials
	 * 
	 * @Request LoanInfoRequestDto propertyValue,propertyType,annualSalary,loanTenure,loanAmount
	 * 
	 * @return LoanInfoResponseDto is the return object which includes
	 * totalAmount,emi,message,statusCode
	 * 
	 */
	
	@Override
	public LoanInfoResponseDto loanInfo(LoanInfoRequestDto loanInforequestDto) {
		log.info("inside loan info service");
		Customer customers=null;
		Optional<Customer> customer=customerRepository.findByCustomerId(loanInforequestDto.getCustomerId());
		Double customerLoanAmount=loanInforequestDto.getLoanAmount();
		Double propertyValue=loanInforequestDto.getPropertyValue();
		Double annualSalary=loanInforequestDto.getAnnualSalary();
		Float rateOfInterest=0.04F;
		Float loanTenure=loanInforequestDto.getLoanTenure();
		Float months=loanTenure*12;
		Double loanAmount=0.0;
		Double emi=0.0;
		Double totalAmount=0.0;
		
		if(!customer.isPresent()) {
			throw new CommonException(ExceptionConstants.ACCOUNT_NOT_FOUND);
		}
		
		if(customer.isPresent()) {
			customers=customer.get();
		}
		
		Period period=Period.between(customers.getDateOfBirth(), LocalDate.now());
		
		if(customerLoanAmount>=propertyValue) {
			throw new CommonException(ExceptionConstants.LOAN_NOT_APPLICABLE);
		}
		
		if(period.getYears()>=18 && period.getYears()<=74) {
			if(annualSalary>=240000 && annualSalary<=600000) {
			loanAmount=propertyValue*0.7;
		    emi= (loanAmount*rateOfInterest*Math. pow(1+rateOfInterest,months))/(Math. pow(1+rateOfInterest,months)-1);
		    totalAmount=emi*months;
			}
		}
		else if(period.getYears()>=75) {
			if(annualSalary>=600001) {
			loanAmount=propertyValue*0.8;
			emi= (loanAmount*rateOfInterest*Math. pow(1+rateOfInterest,months))/(Math. pow(1+rateOfInterest,months)-1);
			totalAmount=emi*months;
			}
		}
		else {
			throw new CommonException(ExceptionConstants.LOAN_INVALID);
		}
		if(customerLoanAmount>=loanAmount) {
			throw new CommonException(ExceptionConstants.LOAN_INVALID);
		}
		
		LoanInfoResponseDto loanInfoResponseDto=new LoanInfoResponseDto();
		loanInfoResponseDto.setTotalAmount(totalAmount);
		loanInfoResponseDto.setEmi(emi);
		loanInfoResponseDto.setRateOfInterest(rateOfInterest);
		loanInfoResponseDto.setStatusCode(201);
		loanInfoResponseDto.setMessage("Loan Enquiry");
		return loanInfoResponseDto;
	}
}