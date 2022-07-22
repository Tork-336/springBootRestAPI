package com.applicationAPI.repository;

import com.applicationAPI.repository.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String usernam, String password);
}
