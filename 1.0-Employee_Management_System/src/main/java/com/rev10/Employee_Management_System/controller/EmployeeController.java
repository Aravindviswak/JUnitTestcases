package com.rev10.Employee_Management_System.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rev10.Employee_Management_System.model.Employee;
import com.rev10.Employee_Management_System.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public String addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/update/{employeeId}")
    public String updateEmployee(@RequestBody Employee employee, @PathVariable Long employeeId) {
        return employeeService.updateEmployee(employee, employeeId);
    }

    @DeleteMapping("/delete/{employeeId}")
    public String deleteEmployee(@PathVariable Long employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }

    @GetMapping("/get/{employeeId}")
    public Employee getEmployeeById(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
