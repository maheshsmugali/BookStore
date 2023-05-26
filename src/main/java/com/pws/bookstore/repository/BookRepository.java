package com.pws.bookstore.repository;


import com.pws.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query("select o from Book o where o.bookName= :bookName")
    Optional<Book> findByName(String bookName);

    @Query("select o from Book o where o.authorName= :authorName and o.bookName= :bookName")
    Optional<Book> findByAuthorName(String authorName,String bookName);
}