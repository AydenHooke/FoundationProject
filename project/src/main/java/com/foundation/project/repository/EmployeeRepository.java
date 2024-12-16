package com.foundation.project.repository;

import java.util.List;

import com.foundation.project.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
   
    //create employee through .save

    Employee findEmployeeByUsername(String username);

    Employee findEmployeeByUsernameAndPassword(String username, String password);

    Employee findEmployeeByEmployeeId(int EmployeeId);
    
    List<Employee> findEmployeesByAccessLevel(int accessLevel);
}
