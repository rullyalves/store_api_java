package com.store.exceptionhandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.store.exceptions.ErrorDetails;
import com.store.exceptions.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException mValidException) {
        List<FieldError> errors = mValidException
        .getBindingResult()
        .getFieldErrors();

        List<ArgumentNotValidDetails> result = errors
        .stream()
        .map(
                e -> 
                new ArgumentNotValidDetails(
                 e.getField(),
                 e.getRejectedValue().toString(),
                 e.getDefaultMessage()))
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(result);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException mException){

            ErrorDetails resourceNotFoundDetails = new ErrorDetails()
            .title("Resource Not Found Exception")
            .status(HttpStatus.NOT_FOUND.value())
            .details(mException.getMessage())
            .timestamp(new Date().getTime())
            .developerMessage("O recurso que você está procurando não foi encontrado");
        
        return new ResponseEntity(resourceNotFoundDetails,HttpStatus.NOT_FOUND);
    }

}

