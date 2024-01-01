package com.example.employeemanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> employeeNotFound(EmployeeNotFoundException employeeNotFoundException){
        ErrorResponse errorResponse = new ErrorResponse("Employee not found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmployeeDoesntExistException.class)
    public ResponseEntity<ErrorResponse> employeeDoesntExist(EmployeeDoesntExistException employeeDoesntExistException){
        ErrorResponse errorResponse = new ErrorResponse(employeeDoesntExistException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseWithDetails> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException){
        Map<String,String> detail = new HashMap<>();
        methodArgumentNotValidException.getFieldErrors().forEach(fieldError -> detail.put(fieldError.getField(), fieldError.getDefaultMessage()));
        ErrorResponseWithDetails response = new ErrorResponseWithDetails();
        response.setMessage("Validation.Exception");
        response.setDetails(detail);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
