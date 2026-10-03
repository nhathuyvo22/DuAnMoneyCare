package com.Huy.Moneycare.service;

import com.Huy.Moneycare.model.Accounts;
import com.Huy.Moneycare.model.Category;
import com.Huy.Moneycare.model.Transaction;
import com.Huy.Moneycare.repository.AccountsRepository;
import com.Huy.Moneycare.repository.CategoryRepository;
import com.Huy.Moneycare.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepo;
    private final AccountsRepository accountsRepo;
    private final CategoryRepository categoryRepo;

    public TransactionService(TransactionRepository transactionRepo,
            AccountsRepository accountsRepo,
            CategoryRepository categoryRepo) {
        this.transactionRepo = transactionRepo;
        this.accountsRepo = accountsRepo;
        this.categoryRepo = categoryRepo;
    }

    // Vừa cập nhật số dư vừa lưu giao dịch: phải thành công cùng nhau
    @Transactional
    public boolean createTransaction(Transaction transaction) {
        Accounts account = accountsRepo.findById(transaction.getAccountId()).orElse(null);
        Category category = categoryRepo.findById(transaction.getCategoryId()).orElse(null);

        if (account == null || category == null) {
            return false;
        }

        if (category.getType().equalsIgnoreCase("EXPENSE")) {
            if (account.getBalance() < transaction.getAmount()) {
                return false; // không đủ tiền
            }
            account.setBalance(account.getBalance() - transaction.getAmount());
        } else if (category.getType().equalsIgnoreCase("INCOME")) {
            account.setBalance(account.getBalance() + transaction.getAmount());
        }

        accountsRepo.save(account);
        transaction.setId(null); // để DB tự sinh id
        transactionRepo.save(transaction);
        return true;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Không tìm thấy giao dịch có id = " + id));
    }

    public boolean deleteTransaction(Long id) {
        if (!transactionRepo.existsById(id)) {
            return false;
        }
        transactionRepo.deleteById(id);
        return true;
    }
}