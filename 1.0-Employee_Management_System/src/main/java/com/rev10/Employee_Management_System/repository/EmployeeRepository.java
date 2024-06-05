package com.rev10.Employee_Management_System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rev10.Employee_Management_System.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
