package com.pws.bookstore.service;

import com.pws.bookstore.dto.*;
import com.pws.bookstore.entity.Book;
import com.pws.bookstore.entity.Orders;
import com.pws.bookstore.entity.User;
import jdk.jfr.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface CommonService {
    void addBooks(BookDto bookDto) throws Exception;

    BookDto getBookById(UUID id) throws Exception;


    Page<Book> getAllTheBooks(int pageNumber,int pageSize);

    void addBooksCategories(String category)throws Exception;

    void addBooksCategoryXref(BookCategoryXrefDto bookCategoryXrefDto) throws Exception;

    void addUsers(UserDto userDto) throws Exception;

    void addingUserBooks(UserBookxrefDto userBookxrefDto) throws Exception;

    List<User> getAllUsers(String bookName) throws Exception;

    List<Book> getAllBooks(String category, int pageNumber,int pageSize);

    List<Orders> findByDate(Date startDate, Date endDate);

    void addOrders(OrderCartDto orderDto) throws Exception;

    void addingToCart(OrderCartDto cartDto) throws Exception;

    Book updateBook(BookDto bookDto) throws Exception;
}
