package com.hcl.mortgage.service;

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
import com.hcl.mortgage.repository.AccountRepository;
import com.hcl.mortgage.repository.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceTest {

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
	
	@Before
	public void setup() {
		
		customer=new Customer();
		customer.setCustomerId(1);
		customer.setPassword("@^+=&");
		customer.setCustomerName("Jyoshna");
		customer.setEmailId("jyoshna@gmail.com");
		LocalDate date=LocalDate.parse("1996-04-12");
		customer.setDateOfBirth(date);

		loanInfoRequestDto=new LoanInfoRequestDto();
		loanInfoRequestDto.setAnnualSalary(300000D);
		loanInfoRequestDto.setCustomerId(1);
		loanInfoRequestDto.setLoanAmount(100000D);
		loanInfoRequestDto.setLoanTenure(10F);
		loanInfoRequestDto.setPropertyType("Home");
		loanInfoRequestDto.setPropertyValue(400000D);
	}
	
	@Test
	public void loanInfo() {
		Optional<Customer> customers=Optional.of(customer);
		Mockito.when(customerRepository.findByCustomerId(Mockito.anyInt())).thenReturn(customers);
		LoanInfoResponseDto loanInfoResponseDto= loanServiceImpl.loanInfo(loanInfoRequestDto);
		Assert.assertNotNull(loanInfoResponseDto);
	}
}
