package com.example.bookstoreuserservice.service;

import com.example.bookstoreuserservice.dto.BookStoreUserDTO;
import com.example.bookstoreuserservice.util.Response;

public interface IUserServices {
    public Response userRegistration(BookStoreUserDTO userDTO);
    public Response userLogin(String emailId, String password);
    public Response getUSer(String token);
    public Response updateUser(String token, BookStoreUserDTO userDTO);
    public Response deleteUser(String token);
    public Response resetPassword(String token,String newPwd);
    public Response forgetPassword(String emailId,String newPwd);
    public Response verifyUser(String token);
    public Response generatingOTP(String token);
    public Response verifyOTP(String token,long otp);
}
