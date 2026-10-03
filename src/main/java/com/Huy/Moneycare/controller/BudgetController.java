package com.Huy.Moneycare.controller;

import com.Huy.Moneycare.model.Budget;
import com.Huy.Moneycare.service.BudgetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService service;

    public BudgetController(BudgetService service) {
        this.service = service;
    }

    @GetMapping
    public List<Budget> getAll() {
        return service.getAllBudgets();
    }

    @GetMapping("/{id}")
    public Budget getById(@PathVariable Long id) {
        return service.getBudgetById(id);
    }

    @PostMapping
    public String create(@RequestBody Budget budget) {
        service.createBudget(budget);
        return "Budget added successfully!";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Budget budget) {
        budget.setId(id);
        return service.updateBudget(budget) ? "Updated!" : "Budget not found";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service.deleteBudget(id) ? "Deleted!" : "Budget not found";
    }
}