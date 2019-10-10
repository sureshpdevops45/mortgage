package com.hcl.mortgage.util;

import java.time.LocalDateTime;

public class ExceptionConstants {

	private ExceptionConstants() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static final String CUSTOMER_NOT_FOUND = "Invalid credentials";
	public static final String ACCOUNT_NOT_FOUND = "Customer Id Not Found";
	public static final String PLEASE_FIND_ACCOUNT_DETAILS = "Please find account details";
	public static final String AGE_INVALID="Enter the valid Date of Birth";
	public static final String MOBILE_INVALID="Enter the valid Mobile Number";
	public static final String EMAIL_INVALID="Enter the valid Email Id";

	public static final String LOAN_INVALID="You are not eligible for applying Loan";
	public static final String LOANNOT_APPLICABLE="Loan Amount is greater than Property Value";
	public static final String LOAN_AMOUNT_INVALID="Invalid Loan Amount";
    public static final String INVALID_ACCOUNT_BALANCE="Customer Salary Account  balance is not sufficient";
	public static final String LOANINFO_UNAVAILABLE="No loan info found for the customer";
	public static final String LOAN_NOT_APPLICABLE="You have already availed the loan";
	
	public static final String LOAN_DEDUCTION = "Loan deducted on " + LocalDateTime.now();

}
