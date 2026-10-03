package com.Huy.Moneycare.controller;

import com.Huy.Moneycare.model.Transaction;
import com.Huy.Moneycare.repository.TransactionRepository;
import com.Huy.Moneycare.service.TransactionService;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;
    private final TransactionRepository transactionRepo;

    public TransactionController(TransactionService service, TransactionRepository transactionRepo) {
        this.service = service;
        this.transactionRepo = transactionRepo;
    }

    @GetMapping
    public List<Transaction> getAll() {
        return service.getAllTransactions();
    }

    // 3 phương thức truy vấn SQL
    @GetMapping("/account/{accountId}")
    public List<Transaction> byAccount(@PathVariable Long accountId) {
        return transactionRepo.findByAccountId(accountId);
    }

    @GetMapping("/search")
    public List<Transaction> search(@RequestParam Long accountId,
            @RequestParam String from,
            @RequestParam String to) {
        return transactionRepo.searchByAccountAndDate(accountId, from, to);
    }

    @GetMapping("/large")
    public List<Transaction> large(@RequestParam Double min) {
        return transactionRepo.findLargeTransactions(min);
    }

    // Chức năng có áp dụng HATEOAS
    @GetMapping("/{id}")
    public EntityModel<Transaction> getById(@PathVariable Long id) {
        Transaction transaction = service.getTransactionById(id);

        EntityModel<Transaction> model = EntityModel.of(transaction);
        model.add(linkTo(methodOn(TransactionController.class).getById(id)).withSelfRel());
        model.add(linkTo(methodOn(TransactionController.class).getAll()).withRel("all-transactions"));

        return model;
    }

    @PostMapping
    public String create(@RequestBody Transaction transaction) {
        boolean ok = service.createTransaction(transaction);
        return ok ? "Transaction added successfully!" : "Failed: account/category not found or insufficient balance";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service.deleteTransaction(id) ? "Deleted!" : "Transaction not found";
    }
}