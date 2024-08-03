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
import com.examserver.services.CategoryService;
import com.examserver.services.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private CategoryService categoryService;

    // add quiz
    @PostMapping("/")
    public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    // update quiz
    @PutMapping("/")
    public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    // get all quizzes
    @GetMapping("/")
    public ResponseEntity<?> getQuizzes() {
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    // get quiz
    @GetMapping("/{quizId}")
    public ResponseEntity<?> getQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(this.quizService.getQuiz(quizId));
    }

    // delete quiz
    @DeleteMapping("/{quizId}")
    public void deleteQuiz(@PathVariable Long quizId) {
        Quiz quiz = this.quizService.getQuiz(quizId);
        Iterable<Question> questions = this.questionRepo.findByQuiz(quiz);
        for (Question question : questions) {
            this.questionRepo.delete(question);
        }
        this.quizService.deleteQuiz(quizId);
    }

    // get quizzes of category
    @GetMapping("/category/{cid}")
    public ResponseEntity<?> getQuizzesOfCategory(@PathVariable Long cid) {
        Category category = this.categoryService.getCategory(cid);
        System.out.println("Category: " + category);

        Iterable<Quiz> quizzes = this.quizService.getQuizzesOfCategory(category);

        System.out.println("Quizzes: " + quizzes);
        return ResponseEntity.ok(quizzes);
    }

    // get active quizzes
    @GetMapping("/active")
    public ResponseEntity<?> getActiveQuizzes() {
        return ResponseEntity.ok(this.quizService.getActiveQuizzes());
    }

    // get active quizzes of category
    @GetMapping("/category/active/{cid}")
    public ResponseEntity<?> getActiveQuizzesOfCategory(@PathVariable Long cid) {
        Category category = this.categoryService.getCategory(cid);
        return ResponseEntity.ok(this.quizService.getActiveQuizzesOfCategory(category));
    }
}
