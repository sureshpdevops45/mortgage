package com.hcl.mortgage.service;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.hcl.mortgage.entity.Customer;
import com.hcl.mortgage.entity.LoanDetails;
import com.hcl.mortgage.exception.CommonException;
import com.hcl.mortgage.repository.CustomerRepository;
import com.hcl.mortgage.repository.LoanRepository;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceTest {
		@Mock
		LoanRepository loanRepository;
		@Mock
		CustomerRepository customerRepository;
		@InjectMocks
		LoanServiceImpl loanService;
		Customer customer=new Customer();
		LoanDetails loanDetail=new LoanDetails();
		@Before
		public void initData() {
			customer.setCustomerId(1);
			customer.setCustomerName("Subashri");
			LocalDate date=LocalDate.parse("1997-07-09");
			customer.setDateOfBirth(date);
			customer.setEmailId("subasridharan0@gmail.com");
			customer.setMobileNumber(9566838401L);
			customer.setPassword("HdZFPXKke4");
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
			Optional<Customer> customerInfo=Optional.of(customer);
			Mockito.when(customerRepository.findByCustomerId(Mockito.anyInt())).thenReturn(customerInfo);
			Mockito.when(loanRepository.findByCustomerId(Mockito.anyInt())).thenReturn(loanDetail);
			LoanDetails loanInfo=loanService.getLoanSummary(1);
			assertNotNull(loanInfo);
			
		}
		@Test(expected = CommonException.class)
		public void testNullLoanSummary() {
			customer=null;
			Optional<Customer> customerInfo=Optional.ofNullable(customer);
			Mockito.when(customerRepository.findByCustomerId(Mockito.anyInt())).thenReturn(customerInfo);
			LoanDetails loanInfo=loanService.getLoanSummary(6);
			assertNull(loanInfo);
			
		}
}
