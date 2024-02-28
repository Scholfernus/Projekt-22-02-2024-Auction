package com.example.auction;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Object>handleObjectNotFoundException(ObjectNotFoundException exception){
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleConstrainValidationException(MethodArgumentNotValidException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, getValidationError(exception));
    }
    private String getValidationError(MethodArgumentNotValidException exception){
        return exception.getFieldErrors()
                .stream()
                .map(error->error.getField() + " " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));
    }
}
