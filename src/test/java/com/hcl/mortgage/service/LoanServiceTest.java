package com.hcl.mortgage.service;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.mortgage.dto.LoanInfoRequestDto;
import com.hcl.mortgage.dto.LoanInfoResponseDto;
import com.hcl.mortgage.entity.Customer;
import com.hcl.mortgage.entity.LoanDetails;
import com.hcl.mortgage.exception.CommonException;
import com.hcl.mortgage.repository.AccountRepository;
import com.hcl.mortgage.repository.CustomerRepository;
import com.hcl.mortgage.repository.LoanRepository;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceTest {

	@Mock
	LoanRepository loanRepository;
	
	
	
	
	@Mock
	CustomerRepository customerRepository;
	
	@Mock
	AccountRepository accountRepository;
	
	@InjectMocks
	LoanServiceImpl loanServiceImpl;
	
	Customer customer;
	
	Optional<Customer> customers;
	
	LoanInfoRequestDto loanInfoRequestDto;
	
	LoanInfoResponseDto loanInfoResponseDto;
	
	LoanDetails loanDetail;
	
	@Before
	public void setup() {
		
		customer=new Customer();
		customer.setCustomerId(1);
		customer.setPassword("@^+=&");
		customer.setCustomerName("Jyoshna");
		customer.setEmailId("jyoshna@gmail.com");
		customer.setMobileNumber(9566838401L);
		LocalDate date=LocalDate.parse("1996-04-12");
		customer.setDateOfBirth(date);

		loanInfoRequestDto=new LoanInfoRequestDto();
		loanInfoRequestDto.setAnnualSalary(300000D);
		loanInfoRequestDto.setCustomerId(1);
		loanInfoRequestDto.setLoanAmount(100000D);
		loanInfoRequestDto.setLoanTenure(10F);
		loanInfoRequestDto.setPropertyType("Home");
		loanInfoRequestDto.setPropertyValue(400000D);
		
		loanDetail=new LoanDetails();
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
	public void loanInfo() {
		Optional<Customer> customers=Optional.of(customer);
		Mockito.when(customerRepository.findByCustomerId(Mockito.anyInt())).thenReturn(customers);
		LoanInfoResponseDto loanInfoResponseDto= loanServiceImpl.loanInfo(loanInfoRequestDto);
		Assert.assertNotNull(loanInfoResponseDto);
	}

		
		@Test
		public void testLoanSummary() {
			Optional<Customer> customerInfo=Optional.of(customer);
			Mockito.when(customerRepository.findByCustomerId(Mockito.anyInt())).thenReturn(customerInfo);
			Mockito.when(loanRepository.findByCustomerId(Mockito.anyInt())).thenReturn(loanDetail);
			LoanDetails loanInfo=loanServiceImpl.getLoanSummary(1);
			assertNotNull(loanInfo);
			
		}
		@Test(expected = CommonException.class)
		public void testNullLoanSummary() {
			customer=null;
			Optional<Customer> customerInfo=Optional.ofNullable(customer);
			Mockito.when(customerRepository.findByCustomerId(Mockito.anyInt())).thenReturn(customerInfo);
			LoanDetails loanInfo=loanServiceImpl.getLoanSummary(6);
			assertNull(loanInfo);
			
		}
}
