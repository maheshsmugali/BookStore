package com.pws.bookstore.dto;

import lombok.*;

@Getter
@Builder
public class UserDto {

    private String name;

    private String email;

    private long phoneNumber;

}