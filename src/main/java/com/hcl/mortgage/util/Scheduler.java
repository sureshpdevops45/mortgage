package com.hcl.mortgage.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hcl.mortgage.entity.Account;
import com.hcl.mortgage.entity.LoanDetails;
import com.hcl.mortgage.entity.Transaction;
import com.hcl.mortgage.exception.CommonException;
import com.hcl.mortgage.repository.AccountRepository;
import com.hcl.mortgage.repository.LoanRepository;
import com.hcl.mortgage.repository.TransactionRepository;

/**
 * @author Shilendra
 *
 */
@Component
public class Scheduler {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Scheduled(cron = "0 0/2 * * * *")
	public void emiScheduler() {
		List<LoanDetails> loans = loanRepository.findAll();

		List<LoanDetails> addedLoans = new ArrayList<>();
		List<Transaction> transactionDetails = new ArrayList<>();
		List<Account> accountData = new ArrayList<>();

		loans.stream().forEach(loan -> {

			LoanDetails loanDetails = new LoanDetails();

			loan.setTotalAmount(loan.getTotalAmount() - loan.getEmi());
			BeanUtils.copyProperties(loan, loanDetails);
			addedLoans.add(loanDetails);

			Integer customer = loan.getCustomerId();
			Account accounts = accountRepository.findByCustomerId(customer);

			if (accounts.getAccountBalance() > loan.getEmi()) {

				accounts.setAccountBalance(accounts.getAccountBalance() - loan.getEmi());
				BeanUtils.copyProperties(accounts, accounts);
				accountData.add(accounts);

				Transaction transaction = new Transaction();
				transaction.setAmountDeducted(loan.getEmi());
				transaction.setCustomerId((Integer) customer);

				transaction.setDescription(ExceptionConstants.LOAN_DEDUCTION);

				transaction.setTransactionDate(LocalDateTime.now());

				transactionDetails.add(transaction);
			}

			else {
				throw new CommonException(ExceptionConstants.INVALID_ACCOUNT_BALANCE);
			}
		});

		accountRepository.saveAll(accountData);
		loanRepository.saveAll(addedLoans);
		transactionRepository.saveAll(transactionDetails);

	}

}
