package com.Huy.Moneycare.controller;

import com.Huy.Moneycare.model.Category;
import com.Huy.Moneycare.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Category> getAll() {
        return service.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        return service.getCategoryById(id);
    }

    @PostMapping
    public String create(@RequestBody Category category) {
        service.createCategory(category);
        return "Category added successfully!";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Category category) {
        category.setId(id);
        return service.updateCategory(category) ? "Updated!" : "Category not found";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service.deleteCategory(id) ? "Deleted!" : "Category not found";
    }
}