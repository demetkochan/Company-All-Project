package com.works.repositories;

import com.works.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmailEqualsAllIgnoreCase(String email);
}
