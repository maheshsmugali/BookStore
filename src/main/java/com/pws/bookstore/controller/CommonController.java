package com.pws.bookstore.controller;

import com.pws.bookstore.dto.*;
import com.pws.bookstore.entity.Book;
import com.pws.bookstore.entity.Orders;
import com.pws.bookstore.entity.User;
import com.pws.bookstore.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequestMapping("/book-store")
@RequiredArgsConstructor
@RestController
public class CommonController {

    private final CommonService commonService;



    @PostMapping("/books")
    public String addBooks(@RequestBody BookDto bookDto) throws Exception {
        commonService.addBooks(bookDto);
        return "Successfully Added Books";
    }

    @GetMapping("/book/id")
    public ResponseEntity<BookDto> getBookById(@RequestParam UUID id) throws Exception {
        BookDto bookDto = commonService.getBookById(id);
        return ResponseEntity.ok(bookDto);
    }

    @PostMapping("/books/categories")
    public String addCategory(@RequestParam String category) throws Exception {
        commonService.addBooksCategories(category);
        return " Successfully Added Books With Categories";
    }

    @GetMapping("/all/books")
    public ResponseEntity<Page<Book>> getAllTheBooks(@RequestParam(defaultValue = "0") int pageNumber,
                                                     @RequestParam(defaultValue = "10") int pageSize) {
        Page<Book> bookPage = commonService.getAllTheBooks(pageNumber, pageSize);
        return ResponseEntity.ok(bookPage);
    }

    @PostMapping("/books/categories/xref")
    public String addBookCategory(@RequestBody BookCategoryXrefDto bookCategoryXrefDto) throws Exception {
        commonService.addBooksCategoryXref(bookCategoryXrefDto);
        return "Successfully Added Books Xref Categories   ";
    }

    @PostMapping("/users")
    public String addUsers(@RequestBody UserDto userDto) throws Exception {
        commonService.addUsers(userDto);
        return "User Added Successfully ";
    }

    @PostMapping("/user-books")
    public String addingUserBook(@RequestBody UserBookxrefDto userBookxrefDto) throws Exception {
        commonService.addingUserBooks(userBookxrefDto);
        return "User Xref Added Successfully ";
    }

    @GetMapping("/userinfo/get-all")
    public List<User> getAllUser(@RequestParam String bookName) throws Exception {
        return commonService.getAllUsers(bookName);
    }

    @GetMapping("/books/get-all")
    public List<Book> getAllBooks(@RequestParam String category, @RequestParam int pageNumber, @RequestParam int pageSize) {
        return commonService.getAllBooks(category, pageNumber, pageSize);
    }

    @PostMapping("/orders")
    public String addOrders(@RequestBody OrderCartDto orderDto) throws Exception {
        commonService.addOrders(orderDto);
        return "order added successfully";
    }

    @PostMapping("/cart")
    public ResponseEntity<Object> addToCart(@RequestBody OrderCartDto cartDto) throws Exception {
        commonService.addingToCart(cartDto);
        return new ResponseEntity<>("Successfully Added To Cart ", HttpStatus.OK);
    }

    @PutMapping("/books/update")
    public ResponseEntity<Book> updateBook(@RequestBody BookDto bookDto) throws Exception {
        Book updatedBook = commonService.updateBook(bookDto);
        return ResponseEntity.ok(updatedBook);
    }

    @GetMapping("/details/get-all")
    public List<Orders> getAllInfo(@DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return commonService.findByDate(startDate, endDate);
    }

}
















//    @PostMapping
//    public ResponseEntity<String> addBooks(@RequestBody BookDto bookDto) {
//        try {
//            commonService.addBooks(bookDto);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Added Books");
//        } catch (Exception exception) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        } catch (Exception exception) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + exception.getMessage());
//        }
//    }