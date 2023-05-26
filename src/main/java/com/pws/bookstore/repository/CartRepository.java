package com.pws.bookstore.repository;


import com.pws.bookstore.entity.Cart;
import com.pws.bookstore.entity.UserBooksXref;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
    @Query("select o from Cart o where o.book.id=:id")
    Optional<Cart> findByBookId(UUID id);

}

