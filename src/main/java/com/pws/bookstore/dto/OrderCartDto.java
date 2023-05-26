package com.pws.bookstore.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;


@Getter
@Builder
public class OrderCartDto {

    private UUID userId;

    private UUID bookId;

    private int quantity;

    private Date date;
}