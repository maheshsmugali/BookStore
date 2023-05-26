package com.pws.bookstore.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
public class UserBookxrefDto {

    private UUID userId;

    private String bookName;
}