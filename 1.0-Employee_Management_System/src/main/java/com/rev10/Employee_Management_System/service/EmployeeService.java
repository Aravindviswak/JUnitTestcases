package com.rev10.Employee_Management_System.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rev10.Employee_Management_System.model.Employee;
import com.rev10.Employee_Management_System.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public String addEmployee(Employee employee) {
        if (employeeRepository.existsById(employee.getEmployeeId())) {
            return "Employee already exists.";
        } else {
            employeeRepository.save(employee);
            return "Employee added successfully.";
        }
    }

    public String updateEmployee(Employee employee, Long employeeId) {
        Optional<Employee> existingEmployee = employeeRepository.findById(employeeId);
        if (existingEmployee.isPresent()) {
            Employee updateEmployee = existingEmployee.get();
            if(employee.getEmployeeName()!=null) {
            	 updateEmployee.setEmployeeName(employee.getEmployeeName());
            }
            if(employee.getEmployeeEmail()!=null) {
            	updateEmployee.setEmployeeEmail(employee.getEmployeeEmail());

           }
            if(employee.getEmployeeSalary()!=0) {
            	  updateEmployee.setEmployeeSalary(employee.getEmployeeSalary());
           }
            if(employee.getEmployeeDepartment()!=null) {
            	  updateEmployee.setEmployeeDepartment(employee.getEmployeeDepartment());
           }
          
                      
          
            employeeRepository.save(updateEmployee);
            return "Employee updated successfully.";
        } else {
            return "Employee not found.";
        }
    }

    public String deleteEmployee(Long employeeId) {
        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
            return "Employee deleted successfully.";
        } else {
            return "Employee not found.";
        }
    }
    

    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}

