package com.hcl.mortgage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mortgage.dto.LoanRequestDto;
import com.hcl.mortgage.dto.LoanResponse;
import com.hcl.mortgage.dto.LoanResponseDto;
import com.hcl.mortgage.entity.LoanDetails;
import com.hcl.mortgage.service.LoanService;

/**
 * @author Subashri Sridharan
 *
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })

public class LoanController {
	@Autowired
	LoanService loanService;
	
	
	/**
	 * Returns Loan Summary Information
	 * @PathVariable customerId
	 */

	@GetMapping("/loans/{customerId}/summary")
	public ResponseEntity<LoanResponseDto> loanSummary(@PathVariable int customerId){
		LoanResponseDto loanResponse=new LoanResponseDto();
		LoanDetails loan=loanService.getLoanSummary(customerId);
		loanResponse.setAccountNumber(loan.getLoanAccountNumber());
		loanResponse.setLoanAmount(loan.getLoanAmount());
		loanResponse.setEmi(loan.getEmi());
		loanResponse.setLoanTenure(loan.getLoanTenure());
		loanResponse.setRateOfInterest(loan.getRateOfInterest());
		loanResponse.setTotalAmount(loan.getTotalAmount());
		return new ResponseEntity<>(loanResponse,HttpStatus.OK);
	}
	
	@PostMapping("/loan")
	public ResponseEntity<LoanResponse> applyLoan(@RequestBody LoanRequestDto loanRequestDto){
		LoanResponse loanResponse=new LoanResponse();
		String message=loanService.applyLoan(loanRequestDto);
		loanResponse.setMessage(message);
		loanResponse.setStatusCode(200);
		return new ResponseEntity<>(loanResponse,HttpStatus.CREATED);
	}
}
