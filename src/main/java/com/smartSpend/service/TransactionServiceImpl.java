package com.smartSpend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartSpend.entity.Transaction;
import com.smartSpend.entity.User;
import com.smartSpend.enums.CategoryType;
import com.smartSpend.repository.TransactionRepository;
import com.smartSpend.repository.UserRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionRepository transactionrepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public void addTransaction(Transaction transaction, String email) {
		User user = userRepo.findByEmail(email).orElse(null);
		transaction.setUser(user);
		transactionrepo.save(transaction);
		
	}

	@Override
	public List<Transaction> getTransactionsByUser(String email) {
		User user = userRepo.findByEmail(email).orElse(null);
		return transactionrepo.findByUser(user);
		
	}

	@Override
	public Transaction getTransactionById(Long id) {
		Transaction transactions = transactionrepo.getReferenceById(id);
		return transactions;
	}

	@Override
	public void updateTransaction(Transaction transaction, String email) {
	    User user = userRepo.findByEmail(email).orElse(null);
	    transaction.setUser(user);
	    transactionrepo.save(transaction);
	}

	@Override
	public void deleteTransaction(Long id) {
		transactionrepo.deleteById(id);
		
	}

	@Override
	public Map<String, Double> getDashboardSummary(String email) {
		
		User user = userRepo.findByEmail(email).orElse(null);
		LocalDate now = LocalDate.now();
		int monthValue = now.getMonthValue();
		int year = now.getYear();
		
		Double income = transactionrepo.getTotalByUserAndTypeAndMonth(user, CategoryType.INCOME, monthValue, year);
		Double expense = transactionrepo.getTotalByUserAndTypeAndMonth(user, CategoryType.EXPENSE, monthValue, year);
		
		if(income==null) income =0.0;
		if(expense==null) expense =0.0;
		
		Double balance= income-expense;
		
		Map<String, Double> summery = new HashMap<>();
		
		summery.put("income", income);
		summery.put("expense", expense);
		summery.put("balance", balance);
		
		return summery;
		
	}

	@Override
	public Map<String, Double> getCategoryWiseExpense(String email) {
		User user = userRepo.findByEmail(email).orElse(null);
		LocalDate now = LocalDate.now();
		int monthValue = now.getMonthValue();
		int year = now.getYear();
		
		List<Object[]> expense = transactionrepo.getCategoryWiseExpense(user, CategoryType.EXPENSE, monthValue, year);
		
		Map<String , Double> catWiseExp = new HashMap<>();
		
		for (Object[] row : expense) {
	        String categoryName = (String) row[0];
	        Double amount = (Double) row[1];
	        catWiseExp.put(categoryName, amount);
	    }

		
		return catWiseExp;
	}

	@Override
	public Map<String, Object> getLastThreeMonthsData(String email) {
	    User user = userRepo.findByEmail(email).orElse(null);
	    LocalDate now = LocalDate.now();

	    List<String> months = new ArrayList<>();
	    List<Double> incomeList = new ArrayList<>();
	    List<Double> expenseList = new ArrayList<>();

	    for (int i = 2; i >= 0; i--) {
	        LocalDate monthDate = now.minusMonths(i);
	        int month = monthDate.getMonthValue();
	        int year = monthDate.getYear();
	        String monthName = monthDate.getMonth().toString(); // e.g. "APRIL"

	        Double income = transactionrepo.getTotalByUserAndTypeAndMonth(user, CategoryType.INCOME, month, year);
	        Double expense = transactionrepo.getTotalByUserAndTypeAndMonth(user, CategoryType.EXPENSE, month, year);

	        if (income == null) income = 0.0;
	        if (expense == null) expense = 0.0;

	        months.add(monthName);
	        incomeList.add(income);
	        expenseList.add(expense);
	    }

	    Map<String, Object> result = new HashMap<>();
	    result.put("months", months);
	    result.put("income", incomeList);
	    result.put("expense", expenseList);

	    return result;
	}
	
	

}
