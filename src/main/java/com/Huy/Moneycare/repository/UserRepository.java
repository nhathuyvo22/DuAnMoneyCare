package com.Huy.Moneycare.repository;

import com.Huy.Moneycare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}