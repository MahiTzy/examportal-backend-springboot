package com.examserver.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examserver.entity.QuizResult;
import com.examserver.services.QuizResultService;

@RestController
@CrossOrigin("*")
public class QuizResultController {

    @Autowired
    private QuizResultService quizResultService;

    @PostMapping("/quiz-result/submit")
    public ResponseEntity<?> saveResultOfQuiz(@RequestBody QuizResult quizResult) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date now = new Date();
        quizResult.setSubmittedAt(formatter.format(now));
        QuizResult quizResult2 = this.quizResultService.saveQuizResult(quizResult);
        return ResponseEntity.ok(quizResult2);
    }

    @GetMapping("/quiz-result/{username}")
    public ResponseEntity<?> getQuizResultByUsername(@PathVariable String username) {
        System.out.println(username);
        return ResponseEntity.ok(this.quizResultService.getQuizResultByUsername(username));
    }

    @GetMapping("/quiz-result/quiz/{qid}")
    public ResponseEntity<?> getQuizResultByQid(@PathVariable Long qid) {
        return ResponseEntity.ok(this.quizResultService.getQuizResultByQid(qid));
    }

}
