package com.Huy.Moneycare.service;

import com.Huy.Moneycare.model.Accounts;
import com.Huy.Moneycare.repository.AccountsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AccountsService {

    private final AccountsRepository accountsRepo;

    public AccountsService(AccountsRepository accountsRepo) {
        this.accountsRepo = accountsRepo;
    }

    public void createAccount(Accounts entity) {
        entity.setId(null);
        accountsRepo.save(entity);
    }

    public List<Accounts> getAllAccounts() {
        return accountsRepo.findAll();
    }

    public Accounts getAccountById(Long id) {
        return accountsRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Không tìm thấy Accounts có id = " + id));
    }

    public boolean updateAccount(Accounts entity) {
        if (entity.getId() == null || !accountsRepo.existsById(entity.getId())) {
            return false;
        }
        accountsRepo.save(entity);
        return true;
    }

    public boolean deleteAccount(Long id) {
        if (!accountsRepo.existsById(id)) {
            return false;
        }
        accountsRepo.deleteById(id);
        return true;
    }
}