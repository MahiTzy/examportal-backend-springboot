package com.examserver.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.examserver.entity.Question;
import com.examserver.entity.Quiz;
import com.examserver.services.QuestionService;
import com.examserver.services.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    // add question
    @PostMapping("/")
    public ResponseEntity<?> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    // update question
    @PutMapping("/")
    public ResponseEntity<?> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    // get all questions
    @GetMapping("/")
    public ResponseEntity<?> getQuestions() {
        return ResponseEntity.ok(this.questionService.getQuestions());
    }

    // get question
    @GetMapping("/{questionId}")
    public ResponseEntity<?> getQuestion(@PathVariable Long questionId) {
        return ResponseEntity.ok(this.questionService.getQuestion(questionId));
    }

    // delete question
    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable Long questionId) {
        this.questionService.deleteQuestion(questionId);
    }

    // get questions of quiz
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable Long quizId) {
        Quiz quiz = this.quizService.getQuiz(quizId);
        if (quiz == null) {
            System.out.println("Quiz not found for ID: " + quizId);
            return ResponseEntity.notFound().build();
        }
        Iterable<Question> questions = this.questionService.getQuestionsOfQuiz(quiz);
        if (questions == null || !questions.iterator().hasNext()) {
            System.out.println("No questions found for Quiz ID: " + quizId);
            return ResponseEntity.ok(Collections.emptyList());
        }
        questions.forEach((question) -> {
            question.setAnswer("");
        });
        List<Question> list = new ArrayList<>();
        questions.forEach(list::add);
        int numberOfQuestions = Integer.parseInt(quiz.getNumberOfQuestions());
        if (list.size() > numberOfQuestions) {
            list = list.subList(0, numberOfQuestions);
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/quiz/all/{quizId}")
    public ResponseEntity<?> getQuestionsOfQuizForAdmin(@PathVariable Long quizId) {
        Quiz quiz = this.quizService.getQuiz(quizId);
        if (quiz == null) {
            System.out.println("Quiz not found for ID: " + quizId);
            return ResponseEntity.notFound().build();
        }
        Iterable<Question> questions = this.questionService.getQuestionsOfQuiz(quiz);
        if (questions == null || !questions.iterator().hasNext()) {
            System.out.println("No questions found for Quiz ID: " + quizId);
            return ResponseEntity.ok(Collections.emptyList());
        }
        List<Question> list = new ArrayList<>();
        questions.forEach(list::add);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/quiz/evaluate")
    public ResponseEntity<?> evaluateQuiz(@RequestBody List<Question> questions) {
        List<Boolean> evaluations = new ArrayList<>();
        double marksGot = 0;
        int correctAnswers = 0;
        int incorrectAnswers = 0;
        int attempted = 0;

        if (questions.isEmpty()) {
            // Handle the case where no questions are provided
            Map<String, Object> response = new HashMap<>();
            response.put("score", marksGot);
            response.put("correct", correctAnswers);
            response.put("wrong", incorrectAnswers);
            response.put("attempt", attempted);
            response.put("evaluations", evaluations);
            return ResponseEntity.ok(response);
        }

        // Calculate marks for a single question
        double marksPerQuestion = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();

        for (Question q : questions) {
            Question storedQuestion = this.questionService.getQuestion(q.getQuesId());

            if (q.getGivenAnswer() != null && !q.getGivenAnswer().trim().isEmpty()) {
                attempted++;
                if (storedQuestion.getAnswer().trim().equals(q.getGivenAnswer().trim())) {
                    correctAnswers++;
                    marksGot += marksPerQuestion;
                    evaluations.add(true);
                } else {
                    incorrectAnswers++;
                    evaluations.add(false);
                }
            } else {
                // Add null to evaluations list for unattempted questions
                evaluations.add(null);
            }
        }

        System.out.println("Marks Got: " + marksGot);
        System.out.println("Correct: " + correctAnswers);
        System.out.println("Attempted: " + attempted);
        System.out.println("Evaluations: " + evaluations);
        System.out.println("Wrong " + incorrectAnswers);

        Map<String, Object> response = new HashMap<>();
        response.put("score", marksGot);
        response.put("correct", correctAnswers);
        response.put("wrong", incorrectAnswers);
        response.put("attempt", attempted);
        response.put("evaluations", evaluations);

        return ResponseEntity.ok(response);
    }

}
