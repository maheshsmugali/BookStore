package com.pws.bookstore.service;


import com.pws.bookstore.dto.*;
import com.pws.bookstore.entity.*;
import com.pws.bookstore.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {


    private final CartRepository cartRepository;


    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    private final OrdersRepository ordersRepository;

    private final UserBookXrefRepository userBookXrefRepository;

    private final BookCategoryXrefRepository bookCategoryXrefRepository;

    private final CategoriesRepository categoriesRepository;

    @Override
    public void addBooks(BookDto bookDto) throws Exception {


        Optional<Book> optionalBook = bookRepository.findByName(bookDto.getBookName());

        Optional<Book> optionalAuthor = bookRepository.findByAuthorName(bookDto.getAuthorName(), bookDto.getBookName());
        if (optionalAuthor.isPresent()) {
            throw new Exception("Author exists with name for this book name: " + bookDto.getAuthorName());
        }
        Book book = Book.builder().bookName(bookDto.getBookName()).price(bookDto.getPrice()).authorName(bookDto.getAuthorName()).build();
        bookRepository.save(book);
    }

    @Override
    public BookDto getBookById(UUID id) throws Exception {
        Optional<Book> optionalBook= bookRepository.findById(id);
        if(!optionalBook.isPresent()){
                throw new Exception("Book not found with id: " + id);
        }
        BookDto bookDto=BookDto.builder().authorName(optionalBook.get().getAuthorName())
                .bookName(optionalBook.get().getBookName())
                .price(optionalBook.get().getPrice())
                .build();
        return bookDto;
    }

    @Override
    public Page<Book> getAllTheBooks(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return bookRepository.findAll(pageable);
    }

    @Override
    public void addBooksCategories(String category) throws Exception {
        Categories categories = Categories.builder().categoryName(category).build();
        categoriesRepository.save(categories);
    }

    @Override
    public void addBooksCategoryXref(BookCategoryXrefDto bookCategoryXrefDto) throws Exception {


        BookCategoryXref booksCategoriesXref = new BookCategoryXref();

        Optional<Book> optionalBook = bookRepository.findById(bookCategoryXrefDto.getBookId());
        if (optionalBook.isPresent()) {
            booksCategoriesXref.setBook(optionalBook.get());
        } else {
            throw new Exception("Book not found for ID: " + bookCategoryXrefDto.getBookId());
        }

        Optional<Categories> optionalCategories = categoriesRepository.findById(bookCategoryXrefDto.getCategoryId());
        if (optionalCategories.isPresent()) {
            booksCategoriesXref.setCategories(optionalCategories.get());
        } else {
            throw new Exception("category not found for ID: " + bookCategoryXrefDto.getCategoryId());
        }

        bookCategoryXrefRepository.save(booksCategoriesXref);
    }

    @Override
    public void addUsers(UserDto userDto) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()) {
            throw new Exception("User Already exist");
        }
        User user = User.builder().userName(userDto.getName()).email(userDto.getEmail()).phoneNumber(userDto.getPhoneNumber()).build();
        userRepository.save(user);
    }

    @Override
    public void addingUserBooks(UserBookxrefDto userBookxrefDto) throws Exception {
        UserBooksXref userBooksXref = new UserBooksXref();

        Optional<User> optionalUser = userRepository.findById(userBookxrefDto.getUserId());
        if (optionalUser.isPresent()) {
            userBooksXref.setUser(optionalUser.get());
        } else {
            throw new Exception("User not found for ID: " + userBookxrefDto.getUserId());
        }

        Optional<Book> optionalBook = bookRepository.findByName(userBookxrefDto.getBookName());
        if (optionalBook.isPresent()) {
            userBooksXref.setBook(optionalBook.get());
        } else {
            throw new Exception("Book not found  " + userBookxrefDto.getBookName());
        }

        userBookXrefRepository.save(userBooksXref);

    }

    @Override
    public List<User> getAllUsers(String bookName) throws Exception {

        Optional<Book> optionalBook = userBookXrefRepository.findByBookName(bookName);
        if (!optionalBook.isPresent()) {
            throw new Exception("Book Name Not Found");
        }
        UUID bookId = optionalBook.get().getId();
        List<User> users = userBookXrefRepository.findByBookId(bookId);
        return users;

    }

    @Override
    public List<Book> getAllBooks(String category, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return bookCategoryXrefRepository.findAllBooks(pageable, category);

    }


    @Override
    public void addOrders(OrderCartDto orderDto) throws Exception {
        Orders orders = new Orders();

        Optional<User> optionalUser = userRepository.findById(orderDto.getUserId());
        if (optionalUser.isPresent()) {
            orders.setUser(optionalUser.get());
        } else {
            throw new Exception("User not found for ID: " + orderDto.getUserId());
        }
        Optional<Book> optionalBook = bookRepository.findById(orderDto.getBookId());
        if (optionalBook.isPresent()) {
            orders.setBook(optionalBook.get());
        } else {
            throw new Exception("Book not found for ID: " + orderDto.getBookId());
        }
        orders.setBook(optionalBook.get());
        orders.setOrderDate(orderDto.getDate());
        ordersRepository.save(orders);

    }

    @Override
    public void addingToCart(OrderCartDto cartDto) throws Exception {
        Optional<User> optionalUser = userRepository.findById(cartDto.getUserId());
        if (optionalUser.isPresent()) {
            Optional<Book> optionalBook = bookRepository.findById(cartDto.getBookId());
            if (optionalBook.isPresent()) {
                Cart cart = Cart.builder().user(optionalUser
                        .get()).book(optionalBook
                        .get()).quantity(cartDto
                        .getQuantity()).build();
                cartRepository.save(cart);
            } else {
                throw new Exception("Book not found for ID: " + cartDto.getBookId());
            }
        } else {
            throw new Exception("User not found for ID: " + cartDto.getUserId());
        }
    }

    @Override
    public Book updateBook(BookDto bookDto) throws Exception {

        Optional<Book> optionalBook = bookRepository.findByName(bookDto.getBookName());
        if (!optionalBook.isPresent()) {
            throw new Exception("Book Not Present with Name " + bookDto.getBookName());
        }
        optionalBook.get().setBookName(bookDto.getBookName());
        optionalBook.get().setPrice(bookDto.getPrice());
        optionalBook.get().setAuthorName(bookDto.getAuthorName());
        return bookRepository.save(optionalBook.get());
    }

    @Override
    public List<Orders> findByDate(Date startDate, Date endDate) {
        List<Orders> orders = ordersRepository.findOrdersByDateRange(startDate, endDate);
        return orders;
    }
}

