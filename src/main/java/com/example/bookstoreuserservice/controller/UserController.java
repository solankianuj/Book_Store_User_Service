package com.example.bookstoreuserservice.controller;

import com.example.bookstoreuserservice.dto.BookStoreUserDTO;
import com.example.bookstoreuserservice.model.BookStoreUser;
import com.example.bookstoreuserservice.service.BookStoreUserServices;
import com.example.bookstoreuserservice.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    BookStoreUserServices bookStoreUserServices;

    @PostMapping("/registration")
    public ResponseEntity<Response>  userRegistration(@RequestBody BookStoreUserDTO userDTO){
        Response response=this.bookStoreUserServices.userRegistration(userDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/login")
    public ResponseEntity<Response> userLogin(@RequestParam(name = "emailId") String emailId,@RequestParam(name = "password") String password){
        Response response=this.bookStoreUserServices.userLogin(emailId,password);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/readUser/{token}")
    public ResponseEntity<Response> getUser(@PathVariable String token){
        Response response=this.bookStoreUserServices.getUSer(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/update/{token}")
    public ResponseEntity<Response> updateUser(@PathVariable String token,@RequestBody BookStoreUserDTO userDTO){
        Response response=this.bookStoreUserServices.updateUser(token,userDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{token}")
    public ResponseEntity<Response> deletingUser(@PathVariable String token){
        Response response=this.bookStoreUserServices.deleteUser(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/forgetPassword")
    public ResponseEntity<Response> forgetPassword(@RequestParam(name = "emailId") String emailId,@RequestParam(name = "newPwd") String newPwd){
        Response response=this.bookStoreUserServices.forgetPassword(emailId,newPwd);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/resetPassword/{token}/{newPassword}")
    public ResponseEntity<Response> deletingUser(@PathVariable String token,@PathVariable String newPassword){
        Response response=this.bookStoreUserServices.resetPassword(token,newPassword);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/verify/{token}")
    public ResponseEntity<Response> verifyUser(@PathVariable String token){
        Response response=this.bookStoreUserServices.verifyUser(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/generateOTP/{token}")
    public ResponseEntity<Response> generatingOtp(@PathVariable String token){
        Response response=this.bookStoreUserServices.generatingOTP(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/verifyOTP/{token}")
    public ResponseEntity<Response> OTPVerification(@PathVariable String token,@RequestParam(name = "OTP") long OTP){
        Response response=this.bookStoreUserServices.verifyOTP(token,OTP);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
