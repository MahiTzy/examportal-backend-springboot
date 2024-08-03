package com.examserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examserver.entity.Category;
import com.examserver.entity.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Long> {

    Iterable<Quiz> findByCategory(Category category);

    Iterable<Quiz> findByActive(Boolean b);

    Iterable<Quiz> findByCategoryAndActive(Category category, Boolean b);

}
