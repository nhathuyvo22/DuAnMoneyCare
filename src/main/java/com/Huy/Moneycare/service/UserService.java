package com.Huy.Moneycare.service;

import com.Huy.Moneycare.model.User;
import com.Huy.Moneycare.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void createUser(User entity) {
        entity.setId(null); // để DB tự sinh id
        userRepo.save(entity);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Không tìm thấy User có id = " + id));
    }

    public boolean updateUser(User entity) {
        if (entity.getId() == null || !userRepo.existsById(entity.getId())) {
            return false;
        }
        userRepo.save(entity);
        return true;
    }

    public boolean deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            return false;
        }
        userRepo.deleteById(id);
        return true;
    }
}