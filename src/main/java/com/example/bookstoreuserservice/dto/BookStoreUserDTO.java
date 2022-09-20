package com.example.bookstoreuserservice.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class BookStoreUserDTO {

    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    private LocalDate dob;
}
