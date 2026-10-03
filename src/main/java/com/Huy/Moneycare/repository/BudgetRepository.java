package com.Huy.Moneycare.repository;

import com.Huy.Moneycare.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}