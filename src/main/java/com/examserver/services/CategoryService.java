package com.examserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examserver.entity.Category;
import com.examserver.repository.CategoryRepo;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category addCategory(Category category) {
        return this.categoryRepo.save(category);
    }

    public Category updateCategory(Category category) {
        return this.categoryRepo.save(category);
    }

    public Category getCategory(Long categoryId) {
        return this.categoryRepo.findById(categoryId).get();
    }

    public void deleteCategory(Long categoryId) {
        this.categoryRepo.deleteById(categoryId);
    }

    public Iterable<Category> getCategories() {
        return this.categoryRepo.findAll();
    }
}
