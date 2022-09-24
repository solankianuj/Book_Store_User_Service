package com.example.bookstoreuserservice.model;

import com.example.bookstoreuserservice.dto.BookStoreUserDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "userData")
@Data
@NoArgsConstructor
public class BookStoreUser {

    @Id
    @GenericGenerator(name = "userData",strategy = "increment")
    @GeneratedValue(generator = "userData")
    private Long userId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate dob;
    private LocalDate registeredDate;
    private LocalDate updateDate;
    private String kyc;
    private Boolean verify;
    private Long oneTimePassword;
    private LocalDate purchaseDate;
    private LocalDate expireDate;

    public BookStoreUser(BookStoreUserDTO userDTO) {
        this.firstName=userDTO.getFirstName();
        this.lastName= userDTO.getLastName();
        this.emailId= userDTO.getEmailId();
        this.password= userDTO.getPassword();
        this.dob=userDTO.getDob();
    }
}
