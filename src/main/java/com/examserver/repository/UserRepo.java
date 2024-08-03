package com.examserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examserver.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public User findByUsername(String username);

}
