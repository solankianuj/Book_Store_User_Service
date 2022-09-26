package com.example.bookstoreuserservice.service;

import com.example.bookstoreuserservice.dto.BookStoreUserDTO;
import com.example.bookstoreuserservice.exception.UserNotFound;
import com.example.bookstoreuserservice.model.BookStoreUser;
import com.example.bookstoreuserservice.repository.BookStoreUserRepository;
import com.example.bookstoreuserservice.service.mailService.MailServices;
import com.example.bookstoreuserservice.util.Response;
import com.example.bookstoreuserservice.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.Random;


/**
 * Purpose-implementation of user operation APIs logic.
 * @author anuj solanki
 * @date 20/09/2022
 * @version 1.0
 */
@Service
public class BookStoreUserServices implements IUserServices {

    @Autowired
    BookStoreUserRepository bookStoreUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    Token tokenUtl;

    @Autowired
    MailServices mailServices;

    /**
     * purpose-Logic implementation of API to register user.
     * @param userDTO
     * @return
     */
    @Override
    public Response userRegistration(BookStoreUserDTO userDTO) {
        BookStoreUser bookStoreUser = new BookStoreUser(userDTO);
        bookStoreUser.setRegisteredDate(LocalDate.now());
        bookStoreUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        bookStoreUserRepository.save(bookStoreUser);
        mailServices.send(userDTO.getEmailId(), "Book Store Registration","Thank-you for registration. Here is your Registration details.\n"+bookStoreUser);
        return new Response("Registration successfully.",200, bookStoreUser);
    }

    /**
     * purpose-Logic implementation of API to login user.
     * @param emailId
     * @param password
     * @return
     */
    @Override
    public Response userLogin(String emailId, String password) {
        Optional<BookStoreUser> bookStoreUser = bookStoreUserRepository.findByEmailId(emailId);
        if (bookStoreUser.isPresent()) {
            if (passwordEncoder.matches(password, bookStoreUser.get().getPassword())) {
                String tokenObj = tokenUtl.createToken(bookStoreUser.get().getUserId());
                return new Response("Login Successful", 200, tokenObj);
            }
            throw new UserNotFound(400, "invalid credential");
        }
        throw new UserNotFound(400, "user not found !");
    }

    /**
     * purpose-Logic implementation of API to fetch user.
     * @param token
     * @return
     */
    @Override
    public Response getUSer(String token) {
        long userId=tokenUtl.decodeToken(token);
        Optional<BookStoreUser> bookStoreUser=bookStoreUserRepository.findById(userId);
        if (bookStoreUser.isPresent()){
            return new Response("Getting User Details",200,bookStoreUser.get());
        }
        throw new UserNotFound(400,"user not found !");
    }

    /**
     * purpose-Logic implementation of API to update user details.
     * @param token
     * @param userDTO
     * @return
     */
    @Override
    public Response updateUser(String token, BookStoreUserDTO userDTO) {
        long userId=tokenUtl.decodeToken(token);
        Optional<BookStoreUser> bookStoreUser=bookStoreUserRepository.findById(userId);
        if (bookStoreUser.isPresent()){
            bookStoreUser.get().setFirstName(userDTO.getFirstName());
            bookStoreUser.get().setLastName(userDTO.getLastName());
            bookStoreUser.get().setEmailId(userDTO.getEmailId());
            bookStoreUser.get().setDob(userDTO.getDob());
            bookStoreUser.get().setUpdateDate(LocalDate.now());
            bookStoreUserRepository.save(bookStoreUser.get());
            return new Response("Updating User Details", 200, bookStoreUser.get());
        }
        throw new UserNotFound(400,"user not found !");
    }

    /**
     * purpose-Logic implementation of API to delete user.
     * @param token
     * @return
     */
    @Override
    public Response deleteUser(String token) {
        long userId=tokenUtl.decodeToken(token);
        Optional<BookStoreUser> bookStoreUser=bookStoreUserRepository.findById(userId);
        if (bookStoreUser.isPresent()){
        bookStoreUserRepository.delete(bookStoreUser.get());
        return new Response("user deleted",200,bookStoreUser.get());
        }
        throw new UserNotFound(400, "user not found !");
    }

    /**
     * purpose-Logic implementation of API to reset password.
     * @param token
     * @param newPwd
     * @return
     */
    @Override
    public Response resetPassword(String token, String newPwd) {
        long userId=tokenUtl.decodeToken(token);
        Optional<BookStoreUser> bookStoreUser=bookStoreUserRepository.findById(userId);
        if (bookStoreUser.isPresent()){
                bookStoreUser.get().setPassword(passwordEncoder.encode(newPwd));
                bookStoreUserRepository.save(bookStoreUser.get());
                return new Response("password change successfully", 200, bookStoreUser.get());

        }
        throw new UserNotFound(400, "user not found !");
    }

    /**
     * purpose-Logic implementation of API to forget password.
     * @param emailId
     * @param newPwd
     * @return
     */
    @Override
    public Response forgetPassword(String emailId,String newPwd) {
        Optional<BookStoreUser> bookStoreUser=bookStoreUserRepository.findByEmailId(emailId);
        if (bookStoreUser.isPresent()){
            String token=tokenUtl.createToken(bookStoreUser.get().getUserId());
            String link=System.getenv("resetPwdLink");
           mailServices.send(emailId,"Generating reset password link",link+token+"/"+newPwd);
            return new Response("Generating Reset Password link ", 200, mailServices);
        }
        throw new UserNotFound(400, "user not found !");
    }

    /**
     * purpose-Logic implementation of API to verify user.
     * @param token
     * @return
     */
    @Override
    public BookStoreUser verifyUser(String token) {
        long userId=tokenUtl.decodeToken(token);
        Optional<BookStoreUser> bookStoreUser=bookStoreUserRepository.findById(userId);
        if (bookStoreUser.isPresent()){
            return bookStoreUser.get();
        }
        throw new UserNotFound(400, "user not found !");

    }

    /**
     * purpose-Logic implementation of API to generate otp.
     * @param token
     * @return
     */
    @Override
    public Response generatingOTP(String token) {
        long userId=tokenUtl.decodeToken(token);
        Optional<BookStoreUser> bookStoreUser=bookStoreUserRepository.findById(userId);
        if (bookStoreUser.isPresent()){
            Random random=new Random();
            Integer oneTimePassword= random.nextInt(10000,999999);
            bookStoreUser.get().setOneTimePassword(oneTimePassword.longValue());
            bookStoreUserRepository.save(bookStoreUser.get());
            return new Response("OTP Generated successfully ",200,bookStoreUser.get());
        }
        throw new UserNotFound(400, "user not found !");
    }

    /**
     * purpose-Logic implementation of API to verify otp.
     * @param token
     * @param otp
     * @return
     */
    @Override
    public Response verifyOTP(String token,long otp) {
        long userId=tokenUtl.decodeToken(token);
        Optional<BookStoreUser> bookStoreUser=bookStoreUserRepository.findById(userId);
        if (bookStoreUser.isPresent()){
            if (otp==bookStoreUser.get().getOneTimePassword()){
                return new Response("OTP Matched ",200,bookStoreUser.get());
            }
            throw new UserNotFound(400, "invalid OTP !");
        }
        throw new UserNotFound(400, "user not found !");
    }

    @Override
    public Response purchesSubscription(String token) {
        if (token!=null){
            long userId=tokenUtl.decodeToken(token);
            Optional<BookStoreUser> bookStoreUser=bookStoreUserRepository.findById(userId);
            if (bookStoreUser.isPresent()){
                bookStoreUser.get().setPurchaseDate(LocalDate.now());
                LocalDate expireDate= bookStoreUser.get().getPurchaseDate().plusYears(1);
                bookStoreUser.get().setExpireDate(expireDate);
                bookStoreUserRepository.save(bookStoreUser.get());
                return new Response("Annual Subscription successfully.",200,bookStoreUser.get());
            }
            throw new UserNotFound(400, "you need to login first !");
        }
        throw new UserNotFound(400, "user not found !");
    }

    @Override
    public Response subscriptionEndingInfo(String token) {
        long userId=tokenUtl.decodeToken(token);
        Optional<BookStoreUser> bookStoreUser=bookStoreUserRepository.findById(userId);
        if (bookStoreUser.isPresent()){
            if (bookStoreUser.get().getExpireDate().equals(LocalDate.now()))
            {
                mailServices.send(bookStoreUser.get().getEmailId(),"Book Store Subscription",bookStoreUser.get().getFirstName()+"  your subscription is ending today,to continue the service update your subscription.\n"+"Thank-you");
                return null;
            }
            throw new UserNotFound(400, "Subscription is not end yet !");
        }
        throw new UserNotFound(400, "user not found !");
    }


}
