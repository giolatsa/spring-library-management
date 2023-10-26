package com.epam.giorgilearn.exception;


import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestGlobalExceptionHandler {



    @ExceptionHandler(ObjectNotFoundException.class)
    protected ResponseEntity<?> handleNotFound(RuntimeException ex){
        Map<String,Object> errorDetails=new HashMap<>();

        errorDetails.put("message", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

    }
}
