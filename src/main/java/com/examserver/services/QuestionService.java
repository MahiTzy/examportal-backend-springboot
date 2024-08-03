package com.examserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examserver.entity.Question;
import com.examserver.entity.Quiz;
import com.examserver.repository.QuestionRepo;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    public void deleteQuestion(Long questionId) {
        this.questionRepo.deleteById(questionId);
    }

    public Iterable<Question> getQuestions() {
        return this.questionRepo.findAll();
    }

    public Question getQuestion(Long questionId) {
        return this.questionRepo.findById(questionId).get();
    }

    public Question addQuestion(Question question) {
        return this.questionRepo.save(question);
    }

    public Question updateQuestion(Question question) {
        return this.questionRepo.save(question);
    }

    public Iterable<Question> getQuestionsOfQuiz(Quiz quiz) {
        return this.questionRepo.findByQuiz(quiz);
    }
}
