package com.rev10.Employee_Management_System.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.rev10.Employee_Management_System.model.Employee;
import com.rev10.Employee_Management_System.repository.EmployeeRepository;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddEmployee_EmployeeAdded() {
        Employee employee = new Employee(1L, "Alice", "alice@example.com", 50000.0, "Engineering");
        when(employeeRepository.existsById(1L)).thenReturn(false);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        String result = employeeService.addEmployee(employee);

        assertEquals("Employee added successfully.", result);
        verify(employeeRepository, times(1)).existsById(1L);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testAddEmployee_EmployeeAlreadyExists() {
        Employee employee = new Employee(1L, "Alice", "alice@example.com", 50000.0, "Engineering");
        when(employeeRepository.existsById(1L)).thenReturn(true);

        String result = employeeService.addEmployee(employee);

        assertEquals("Employee already exists.", result);
        verify(employeeRepository, times(1)).existsById(1L);
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void testUpdateEmployee_EmployeeUpdated() {
        Employee employee = new Employee(1L, "Alice", "alice@example.com", 50000.0, "Engineering");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        String result = employeeService.updateEmployee(employee, 1L);

        assertEquals("Employee updated successfully.", result);
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(employee);
    }
    

    @Test
    void testUpdateEmployee_EmployeeNotFound() {
    	Employee employee = new Employee(1L, "Alice", "alice@example.com", 50000.0, "Engineering");
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        String result = employeeService.updateEmployee(employee, 1L);

        assertEquals("Employee not found.", result);
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void testDeleteEmployee_EmployeeDeleted() {
        when(employeeRepository.existsById(1L)).thenReturn(true);

        String result = employeeService.deleteEmployee(1L);

        assertEquals("Employee deleted successfully.", result);
        verify(employeeRepository, times(1)).existsById(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteEmployee_EmployeeNotFound() {
        when(employeeRepository.existsById(1L)).thenReturn(false);

        String result = employeeService.deleteEmployee(1L);

        assertEquals("Employee not found.", result);
        verify(employeeRepository, times(1)).existsById(1L);
        verify(employeeRepository, never()).deleteById(1L);
    }

    @Test
    void testGetEmployeeById_EmployeeFound() {
        Employee employee = new Employee(1L, "Alice", "alice@example.com", 50000.0, "Engineering");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(1L);

        assertNotNull(result);
        assertEquals(employee.getEmployeeId(), result.getEmployeeId());
        assertEquals(employee.getEmployeeName(), result.getEmployeeName());
        assertEquals(employee.getEmployeeEmail(), result.getEmployeeEmail());
        assertEquals(employee.getEmployeeSalary(), result.getEmployeeSalary());
        assertEquals(employee.getEmployeeDepartment(), result.getEmployeeDepartment());

        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetEmployeeById_EmployeeNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        Employee result = employeeService.getEmployeeById(1L);

        assertNull(result);
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllEmployees_EmployeesFound() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "Alice", "alice@example.com", 50000.0, "Engineering"));
        employees.add(new Employee(2L, "Bob", "bob@example.com", 60000.0, "Sales"));

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertEquals(employees.size(), result.size());
        assertEquals(employees.get(0).getEmployeeName(), result.get(0).getEmployeeName());
        assertEquals(employees.get(1).getEmployeeName(), result.get(1).getEmployeeName());

        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testGetAllEmployees_NoEmployeesFound() {
        List<Employee> employees = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(employeeRepository, times(1)).findAll();
    }
}
