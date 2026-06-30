package com.smartSpend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smartSpend.entity.Transaction;
import com.smartSpend.entity.User;
import com.smartSpend.enums.CategoryType;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	List<Transaction>findByUser( User user);
	
	@Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user = :user AND t.category.type = :type " +
		       "AND MONTH(t.date) = :month AND YEAR(t.date) = :year")
		Double getTotalByUserAndTypeAndMonth(
		    @Param("user") User user, 
		    @Param("type") CategoryType type, 
		    @Param("month") int month, 
		    @Param("year") int year
		);
	
	@Query("SELECT t.category.name, SUM(t.amount) FROM Transaction t " +
		       "WHERE t.user = :user AND t.category.type = :type " +
		       "AND MONTH(t.date) = :month AND YEAR(t.date) = :year " +
		       "GROUP BY t.category.name")
		List<Object[]> getCategoryWiseExpense(
		    @Param("user") User user,
		    @Param("type") CategoryType type,
		    @Param("month") int months,
		    @Param("year") int year
		);	
}
