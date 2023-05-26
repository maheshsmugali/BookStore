package com.pws.bookstore.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
public class BookCategoryXrefDto {

    private UUID bookId;

    private UUID categoryId;

}