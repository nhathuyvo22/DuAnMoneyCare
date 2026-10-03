package com.Huy.Moneycare.repository;

import com.Huy.Moneycare.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
}