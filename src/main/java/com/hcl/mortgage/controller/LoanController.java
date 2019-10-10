package com.hcl.mortgage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mortgage.dto.LoanInfoRequestDto;
import com.hcl.mortgage.dto.LoanInfoResponseDto;
import com.hcl.mortgage.service.LoanService;

@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class LoanController {

	@Autowired
	LoanService loanServcie;
	
	@PostMapping("/loanInfo")
	public ResponseEntity<LoanInfoResponseDto> info(@RequestBody LoanInfoRequestDto loanInfoRequestDto){
		LoanInfoResponseDto loanInfoResponseDto=loanServcie.loanInfo(loanInfoRequestDto);
		return new ResponseEntity<>(loanInfoResponseDto, HttpStatus.CREATED);
	}
}

