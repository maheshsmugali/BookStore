package com.pws.bookstore.dto;

import lombok.*;

@Getter
@Builder
public class BookDto {

    private String  bookName;

    private String authorName;

    private double price;
}

