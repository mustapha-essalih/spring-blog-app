package dev.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import dev.API.model.entity.RootUser;

@Repository
public interface UserRepository extends JpaRepository<RootUser , Integer>{
    RootUser findByUsername(String username);
}
