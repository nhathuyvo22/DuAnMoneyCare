package com.Huy.Moneycare.service;

import com.Huy.Moneycare.model.Category;
import com.Huy.Moneycare.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepo;

    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public void createCategory(Category entity) {
        entity.setId(null); // để DB tự sinh id
        categoryRepo.save(entity);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Không tìm thấy Category có id = " + id));
    }

    public boolean updateCategory(Category entity) {
        if (entity.getId() == null || !categoryRepo.existsById(entity.getId())) {
            return false;
        }
        categoryRepo.save(entity);
        return true;
    }

    public boolean deleteCategory(Long id) {
        if (!categoryRepo.existsById(id)) {
            return false;
        }
        categoryRepo.deleteById(id);
        return true;
    }
}