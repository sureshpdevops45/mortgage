package com.hcl.mortgage.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.mortgage.dto.LoanResponseDto;
import com.hcl.mortgage.entity.LoanDetails;
import com.hcl.mortgage.service.LoanServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class LoanControllerTest {
	
	@Mock 
	LoanServiceImpl loanService;
	@InjectMocks
	LoanController loanController;
	LoanDetails loanDetail=new LoanDetails();
	@Before
	public void initData() {
		loanDetail.setCustomerId(1);
		loanDetail.setEmi(35000.0);
		loanDetail.setLoanAccountNumber(8763657238L);
		loanDetail.setLoanAmount(450000.0);
		loanDetail.setLoanId(1);
		loanDetail.setLoanTenure(3F);
		loanDetail.setPropertyType("Home");
		loanDetail.setPropertyValue(8500000.0);
		loanDetail.setRateOfInterest(4F);
		loanDetail.setTotalAmount(600000.0);
		
	}
	
	@Test
	public void testLoanSummary() {
		Mockito.when(loanService.getLoanSummary(Mockito.anyInt())).thenReturn(loanDetail);
		ResponseEntity<LoanResponseDto> loanSummary=loanController.loanSummary(1);
		assertNotNull(loanSummary);
		assertEquals(200,loanSummary.getStatusCode().value());
	}
}
