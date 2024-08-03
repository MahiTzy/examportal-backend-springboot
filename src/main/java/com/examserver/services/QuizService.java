package com.examserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examserver.entity.Category;
import com.examserver.entity.Quiz;
import com.examserver.repository.QuizRepo;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;
    
    public void deleteQuiz(Long quizId) {
        this.quizRepo.deleteById(quizId);
    }

    public Iterable<Quiz> getQuizzes() {
        return this.quizRepo.findAll();
    }

    public Quiz getQuiz(Long quizId) {
        return this.quizRepo.findById(quizId).get();
    }

    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepo.save(quiz);
    }

    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepo.save(quiz);
    }

    public Iterable<Quiz> getQuizzesOfCategory(Category category) {
        return this.quizRepo.findByCategory(category);
    }

    public Iterable<Quiz> getActiveQuizzes() {
        return this.quizRepo.findByActive(true);
    }

    public Iterable<Quiz> getActiveQuizzesOfCategory(Category category) {
        return this.quizRepo.findByCategoryAndActive(category, true);
    }
}
