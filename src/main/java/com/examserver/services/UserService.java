package com.examserver.services;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examserver.entity.User;
import com.examserver.entity.UserRole;
import com.examserver.helper.UserFoundException;
import com.examserver.repository.RoleRepo;
import com.examserver.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private RoleRepo roleRepository;

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User localUser = userRepository.findByUsername(user.getUsername());
        if (localUser != null) {
            LOG.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
            throw new UserFoundException("User with username " + user.getUsername() + " is already exists!");
        } else {
            for (UserRole uRole : userRoles) {
                roleRepository.save(uRole.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            localUser = userRepository.save(user);
        }
        return localUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
