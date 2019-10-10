package com.hcl.mortgage.dto;

public class LoanResponseDto {
		
		private Long accountNumber;
		private Double totalAmount;
		private Double loanAmount;
		private Float loanTenure;
		private Float rateOfInterest;
		private Double emi;
		public Long getAccountNumber() {
			return accountNumber;
		}
		public void setAccountNumber(Long accountNumber) {
			this.accountNumber = accountNumber;
		}
		public Double getTotalAmount() {
			return totalAmount;
		}
		public void setTotalAmount(Double totalAmount) {
			this.totalAmount = totalAmount;
		}
		public Double getLoanAmount() {
			return loanAmount;
		}
		public void setLoanAmount(Double loanAmount) {
			this.loanAmount = loanAmount;
		}
		public Float getLoanTenure() {
			return loanTenure;
		}
		public void setLoanTenure(Float loanTenure) {
			this.loanTenure = loanTenure;
		}
		
		public Float getRateOfInterest() {
			return rateOfInterest;
		}
		public void setRateOfInterest(Float rateOfInterest) {
			this.rateOfInterest = rateOfInterest;
		}
		public Double getEmi() {
			return emi;
		}
		public void setEmi(Double emi) {
			this.emi = emi;
		}
		
}
