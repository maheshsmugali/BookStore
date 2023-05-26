package com.pws.bookstore.repository;

import com.pws.bookstore.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriesRepository extends JpaRepository<Categories, UUID> {
}
