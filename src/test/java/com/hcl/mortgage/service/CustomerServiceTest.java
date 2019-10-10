package com.hcl.mortgage.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.mail.javamail.JavaMailSender;

import com.hcl.mortgage.dto.CustomerRequestDto;
import com.hcl.mortgage.entity.Account;
import com.hcl.mortgage.entity.Customer;
import com.hcl.mortgage.exception.CommonException;
import com.hcl.mortgage.repository.AccountRepository;
import com.hcl.mortgage.repository.CustomerRepository;
import com.hcl.mortgage.util.Email;
import com.hcl.mortgage.util.Sms;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
	
	@Mock
	CustomerRepository customerRepository;
	@Mock
	AccountRepository accountRepository;
	@InjectMocks
	CustomerServiceImpl customerService;
	@Mock
	JavaMailSender javaMailSender;
	String message=null;
	CustomerRequestDto customerRequestDto=new CustomerRequestDto();
	Customer customer=new Customer();
	Account account=new Account();
	Sms sms=new Sms();
	Email email=new Email();
	@Before
	public void initData() {
		customerRequestDto.setCustomerName("Subashri");
		customerRequestDto.setDob("1997/07/09");
		customerRequestDto.setEmailId("subasridharan0@gmail.com");
		customerRequestDto.setMobileNumber(9566838401L);
		message="Registered Successfully";
		BeanUtils.copyProperties(customerRequestDto, customer);
		account.setAccountBalance(10000.0);
		account.setAccountNumber(7632360839L);
		account.setCustomerId(1);
		
	}
	
	@Test
	public void testRegisterCustomer() {
		Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customer);
		Mockito.when(accountRepository.save(Mockito.any())).thenReturn(account);
		String info=customerService.registerCustomer(customerRequestDto);
		assertNotNull(info);
		assertEquals(message,info);
	}
	
	@Test(expected = CommonException.class)
	public void ageTest() {
		customerRequestDto.setDob("2002/04/02");
		String info=customerService.registerCustomer(customerRequestDto);
		assertEquals(message,info);
	}
	
	@Test(expected = CommonException.class)
	public void mobileTest() {
		customerRequestDto.setMobileNumber(2345L);
		String info=customerService.registerCustomer(customerRequestDto);
		assertEquals(message,info);
	}
	@Test(expected = CommonException.class)
	public void emailTest() {
		customerRequestDto.setEmailId("kabhil.com");
		String info=customerService.registerCustomer(customerRequestDto);
		assertEquals(message,info);
	}
}
