package com.example.INTERSWITCH.REPOSITORY;

import com.example.INTERSWITCH.ENTITY.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
