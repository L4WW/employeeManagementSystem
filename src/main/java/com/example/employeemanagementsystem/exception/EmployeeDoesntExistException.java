package com.example.employeemanagementsystem.exception;

public class EmployeeDoesntExistException extends RuntimeException {
    public EmployeeDoesntExistException(String message){
        super(message);
    }
}
