package com.examserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examserver.entity.Question;
import com.examserver.entity.Quiz;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {

    Iterable<Question> findByQuiz(Quiz quiz);

    void deleteByQuiz(Quiz quiz);

}
