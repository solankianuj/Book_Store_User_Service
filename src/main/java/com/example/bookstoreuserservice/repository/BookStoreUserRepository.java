package com.example.bookstoreuserservice.repository;

import com.example.bookstoreuserservice.model.BookStoreUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookStoreUserRepository extends JpaRepository<BookStoreUser,Long> {
    Optional<BookStoreUser> findByEmailId(String emilId);
}
