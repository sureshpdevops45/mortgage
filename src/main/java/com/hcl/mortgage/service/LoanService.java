package com.hcl.mortgage.service;

import com.hcl.mortgage.entity.LoanDetails;

public interface LoanService {

	LoanDetails getLoanSummary(int customerId);
}
