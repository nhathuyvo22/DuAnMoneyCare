package com.Huy.Moneycare.controller;

import com.Huy.Moneycare.model.Accounts;
import com.Huy.Moneycare.service.AccountsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {

    private final AccountsService service;

    public AccountsController(AccountsService service) {
        this.service = service;
    }

    @GetMapping
    public List<Accounts> getAll() {
        return service.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Accounts getById(@PathVariable Long id) {
        return service.getAccountById(id);
    }

    @PostMapping
    public String create(@RequestBody Accounts account) {
        service.createAccount(account);
        return "Account added successfully!";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Accounts account) {
        account.setId(id);
        return service.updateAccount(account) ? "Updated!" : "Account not found";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service.deleteAccount(id) ? "Deleted!" : "Account not found";
    }
}