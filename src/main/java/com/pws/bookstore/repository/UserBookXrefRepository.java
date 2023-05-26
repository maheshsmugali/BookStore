package com.pws.bookstore.repository;

import com.pws.bookstore.entity.Book;
import com.pws.bookstore.entity.User;
import com.pws.bookstore.entity.UserBooksXref;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserBookXrefRepository extends JpaRepository<UserBooksXref, UUID> {
    @Query("select o.book from UserBooksXref o where o.book.bookName=:bookName")
    Optional<Book> findByBookName(String bookName);
    @Query("select o.user from UserBooksXref o where o.book.id=:bookId")
    List<User> findByBookId(UUID bookId);
    @Query("select o from UserBooksXref o where o.book.id=:bookId")
    Optional<UserBooksXref> findByBooksId(UUID bookId);

}