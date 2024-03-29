package com.example.bookstoreuserservice.exception;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.example.bookstoreuserservice.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class UserNotFoundExceptionHandler {
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Response> handleCandidateException(UserNotFound adminNotFound){
        Response response=new Response();
        response.setStatusCode(400);
        response.setStatusMsg(adminNotFound.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleException(MethodArgumentNotValidException ad) {
        List<ObjectError> objectErrors=ad.getBindingResult().getAllErrors();
        List<String> Message =objectErrors.stream().map(objErr-> objErr.getDefaultMessage()).collect(Collectors.toList());
        Response response = new Response();
        response.setStatusMsg(Message.toString());
        response.setStatusCode(400);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SignatureVerificationException.class)
    public ResponseEntity<Response> handleException(SignatureVerificationException ad) {
        Response response=new Response();
        response.setStatusCode(400);
        response.setStatusMsg("Invalid Token !");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
