package com.smartSpend.service;

import java.util.List;
import java.util.Map;

import com.smartSpend.entity.Transaction;

public interface TransactionService {
	void addTransaction(Transaction transaction, String email);
	List<Transaction> getTransactionsByUser(String email);
	Transaction getTransactionById(Long id);
	void  updateTransaction(Transaction transaction, String email);
	void deleteTransaction(Long id);
	Map<String, Double> getDashboardSummary(String email);
	Map<String, Double> getCategoryWiseExpense(String email);
	public Map<String, Object> getLastThreeMonthsData(String email);
}
