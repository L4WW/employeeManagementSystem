package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.dto.request.CreateEmployeeRequest;
import com.example.employeemanagementsystem.dto.request.UpdateEmployeeByIdRequest;
import com.example.employeemanagementsystem.dto.response.GetAllEmployeeResponse;
import com.example.employeemanagementsystem.dto.response.GetEmployeeByIdResponse;
import com.example.employeemanagementsystem.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void CreateEmployee(@RequestBody @Valid CreateEmployeeRequest request){
        this.employeeService.createEmployee(request);
    }
    @GetMapping("/getall")
    public List<GetAllEmployeeResponse> getAllEmployees(){

        return employeeService.getAllEmployees();
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployeeById(@PathVariable Long id){
        this.employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/getbyid/{id}")
    public GetEmployeeByIdResponse getEmployeeById(@PathVariable Long id){
        return this.employeeService.getEmployeeById(id);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmployeeById(@RequestBody @Valid UpdateEmployeeByIdRequest request){
        this.employeeService.updateEmployeeById(request);

    }

}
