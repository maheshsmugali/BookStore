package com.pws.bookstore.repository;

import com.pws.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("select o from User o where o.email= :email")
    Optional<User> findByEmail(String email);
}