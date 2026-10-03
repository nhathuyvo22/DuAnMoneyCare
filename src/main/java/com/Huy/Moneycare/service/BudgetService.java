package com.Huy.Moneycare.service;

import com.Huy.Moneycare.model.Budget;
import com.Huy.Moneycare.repository.BudgetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepo;

    public BudgetService(BudgetRepository budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public void createBudget(Budget entity) {
        entity.setId(null); // để DB tự sinh id
        budgetRepo.save(entity);
    }

    public List<Budget> getAllBudgets() {
        return budgetRepo.findAll();
    }

    public Budget getBudgetById(Long id) {
        return budgetRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Không tìm thấy Budget có id = " + id));
    }

    public boolean updateBudget(Budget entity) {
        if (entity.getId() == null || !budgetRepo.existsById(entity.getId())) {
            return false;
        }
        budgetRepo.save(entity);
        return true;
    }

    public boolean deleteBudget(Long id) {
        if (!budgetRepo.existsById(id)) {
            return false;
        }
        budgetRepo.deleteById(id);
        return true;
    }
}