package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.dto.request.CreateEmployeeRequest;
import com.example.employeemanagementsystem.dto.request.UpdateEmployeeByIdRequest;
import com.example.employeemanagementsystem.dto.response.GetAllEmployeeResponse;
import com.example.employeemanagementsystem.dto.response.GetEmployeeByIdResponse;
import com.example.employeemanagementsystem.exception.EmployeeDoesntExistException;
import com.example.employeemanagementsystem.exception.EmployeeNotFoundException;
import com.example.employeemanagementsystem.model.Employee;
import com.example.employeemanagementsystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;
    public void createEmployee(CreateEmployeeRequest request) {
        Employee employee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();
        this.repository.save(employee);
    }

    public List<GetAllEmployeeResponse> getAllEmployees() {
        List<Employee> employees = repository.findAll();

        return employees.stream().map(this::mapToGetAllEmployeesResponse).collect(Collectors.toList());
    }

    public void deleteEmployeeById(Long id){
        this.repository.findById(id).orElseThrow(()->new EmployeeDoesntExistException("Employee doesn't exist"));
        this.repository.deleteById(id);
    }

    private GetAllEmployeeResponse mapToGetAllEmployeesResponse(Employee employee) {
        return GetAllEmployeeResponse.builder()
                .id(employee.getId())
                .email(employee.getEmail())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .build();
    }

    public GetEmployeeByIdResponse getEmployeeById(Long id) {
        Employee employee = this.repository.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee not found"));
        return GetEmployeeByIdResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
    }

    public void updateEmployeeById(UpdateEmployeeByIdRequest request) {
        Employee employee = this.repository.findById(request.getId()).orElseThrow();
        employee.setEmail(request.getEmail());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        this.repository.save(employee);
    }
}
