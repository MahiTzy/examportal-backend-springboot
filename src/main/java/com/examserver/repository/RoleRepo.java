package com.examserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examserver.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    public Role findByRoleName(String roleName);

}
