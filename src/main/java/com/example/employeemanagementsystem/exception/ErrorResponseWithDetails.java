package com.example.employeemanagementsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseWithDetails extends ErrorResponse {
    private Map<String,String> details;
}
