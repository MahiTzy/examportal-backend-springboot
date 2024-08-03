package com.examserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examserver.entity.QuizResult;
import com.examserver.repository.QuizResultRepo;

@Service
public class QuizResultService {

    @Autowired
    private QuizResultRepo quizResultRepo;

    public QuizResult saveQuizResult(QuizResult quizResult) {
        return this.quizResultRepo.save(quizResult);
    }

    // find by username
    public Iterable<QuizResult> getQuizResultByUsername(String username) {
        return this.quizResultRepo.findByUsername(username);
    }

    // find by qid
    public Iterable<QuizResult> getQuizResultByQid(Long qid) {
        return this.quizResultRepo.findByQid(qid);
    }

}
