package com.examserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examserver.entity.Category;
import com.examserver.entity.Question;
import com.examserver.entity.Quiz;
import com.examserver.repository.QuestionRepo;
import com.examserver.repository.QuizRepo;
import com.examserver.services.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired 
    private QuizRepo quizRepo;

    @Autowired 
    private QuestionRepo questionRepo;

    // add category
    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok(this.categoryService.addCategory(category));
    }

    // update category
    @PutMapping("/")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        return ResponseEntity.ok(this.categoryService.updateCategory(category));
    }

    // get category
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(this.categoryService.getCategory(categoryId));
    }

    // get all categories
    @GetMapping("/")
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(this.categoryService.getCategories());
    }

    // delete category
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        Category category = this.categoryService.getCategory(categoryId);
        Iterable<Quiz> quizzes = this.quizRepo.findByCategory(category);
        for (Quiz quiz : quizzes) {
            Iterable<Question> questions = this.questionRepo.findByQuiz(quiz);
            for (Question question : questions) {
                this.questionRepo.delete(question);
            }
            this.quizRepo.delete(quiz);
        }
        this.categoryService.deleteCategory(categoryId);
    }
}
