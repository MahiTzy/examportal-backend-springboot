package com.examserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examserver.entity.QuizResult;

@Repository
public interface QuizResultRepo extends JpaRepository<QuizResult, Long> {

    public Iterable<QuizResult> findByUsername(String username);

    public Iterable<QuizResult> findByQid(Long qid);
}
