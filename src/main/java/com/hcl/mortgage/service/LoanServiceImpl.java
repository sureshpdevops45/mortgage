package com.hcl.mortgage.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcl.mortgage.entity.Customer;
import com.hcl.mortgage.entity.LoanDetails;
import com.hcl.mortgage.exception.CommonException;
import com.hcl.mortgage.repository.CustomerRepository;
import com.hcl.mortgage.repository.LoanRepository;
import com.hcl.mortgage.util.ExceptionConstants;


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

	
}
