package com.example.bookstoreuserservice.controller;

import com.example.bookstoreuserservice.dto.BookStoreUserDTO;
import com.example.bookstoreuserservice.model.BookStoreUser;
import com.example.bookstoreuserservice.service.BookStoreUserServices;
import com.example.bookstoreuserservice.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Purpose-Creating user operation API'S.
 * @author anuj solanki
 * @date 20/09/2022
 * @version 1.0
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    BookStoreUserServices bookStoreUserServices;

    /**
     * Purpose-API to register user.
     * @param userDTO
     * @return
     */

    @PostMapping("/registration")
    public ResponseEntity<Response>  userRegistration(@RequestBody BookStoreUserDTO userDTO){
        Response response=this.bookStoreUserServices.userRegistration(userDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/purchesSubscription/{token}")
    public ResponseEntity<Response>  subscription(@PathVariable String token){
        Response response=this.bookStoreUserServices.purchesSubscription(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to user login.
     * @param emailId
     * @param password
     * @return
     */
    @GetMapping("/login")
    public ResponseEntity<Response> userLogin(@RequestParam(name = "emailId") String emailId,@RequestParam(name = "password") String password){
        Response response=this.bookStoreUserServices.userLogin(emailId,password);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to fetch user.
     * @param token
     * @return
     */
    @GetMapping("/readUser/{token}")
    public ResponseEntity<Response> getUser(@PathVariable String token){
        Response response=this.bookStoreUserServices.getUSer(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to update user details.
     * @param token
     * @param userDTO
     * @return
     */
    @PutMapping("/update/{token}")
    public ResponseEntity<Response> updateUser(@PathVariable String token,@RequestBody BookStoreUserDTO userDTO){
        Response response=this.bookStoreUserServices.updateUser(token,userDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to delete user.
     * @param token
     * @return
     */
    @DeleteMapping("/delete/{token}")
    public ResponseEntity<Response> deletingUser(@PathVariable String token){
        Response response=this.bookStoreUserServices.deleteUser(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to forget password.
     * @param emailId
     * @param newPwd
     * @return
     */
    @GetMapping("/forgetPassword")
    public ResponseEntity<Response> forgetPassword(@RequestParam(name = "emailId") String emailId,@RequestParam(name = "newPwd") String newPwd){
        Response response=this.bookStoreUserServices.forgetPassword(emailId,newPwd);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to reset password.
     * @param token
     * @param newPassword
     * @return
     */
    @GetMapping("/resetPassword/{token}/{newPassword}")
    public ResponseEntity<Response> deletingUser(@PathVariable String token,@PathVariable String newPassword){
        Response response=this.bookStoreUserServices.resetPassword(token,newPassword);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to verify user token.
     * @param token
     * @return
     */
    @GetMapping("/verify/{token}")
    public ResponseEntity<BookStoreUser> verifyUser(@PathVariable String token){
        BookStoreUser bookStoreUser=this.bookStoreUserServices.verifyUser(token);
        return new ResponseEntity<>(bookStoreUser, HttpStatus.OK);
    }

    /**
     * Purpose-API to generate otp.
     * @param token
     * @return
     */
    @GetMapping("/generateOTP/{token}")
    public ResponseEntity<Response> generatingOtp(@PathVariable String token){
        Response response=this.bookStoreUserServices.generatingOTP(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to verify otp.
     * @param token
     * @param OTP
     * @return
     */
    @GetMapping("/verifyOTP/{token}")
    public ResponseEntity<Response> OTPVerification(@PathVariable String token,@RequestParam(name = "OTP") long OTP){
        Response response=this.bookStoreUserServices.verifyOTP(token,OTP);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/expire/{token}")
    public ResponseEntity<Response> expireDetails(@PathVariable String token){
        Response response=this.bookStoreUserServices.subscriptionEndingInfo(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
