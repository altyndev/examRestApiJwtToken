package com.peaksoft.examrestapijwttoken.exception.handler;

import com.peaksoft.examrestapijwttoken.exception.ExceptionResponse;
import com.peaksoft.examrestapijwttoken.exception.ThereIsSuchAName;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 500

    // 400
    //400
    @ExceptionHandler(ThereIsSuchAName.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ExceptionResponse handlerNotFoundException(ThereIsSuchAName e){
        return new ExceptionResponse(
                HttpStatus.FOUND,
                e.getClass().getSimpleName(),
                e.getMessage()
        );

    }
    // 404
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handlerBookFoundException(NotFoundException e) {

        return new ExceptionResponse(
                HttpStatus.NOT_FOUND,
                e.getClass().getSimpleName(),
                e.getMessage()
        );
    }
}
