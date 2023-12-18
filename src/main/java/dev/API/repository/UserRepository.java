package dev.API.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.API.entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User , Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
