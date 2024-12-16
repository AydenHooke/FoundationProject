package com.foundation.project.service;

import com.foundation.project.entity.Employee;
import com.foundation.project.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    //persist an employee
    public Employee createEmployee(Employee employee){
        if(employee.getUsername().length() > 0)
            if(employee.getPassword().length() > 0)
                return this.employeeRepository.save(employee);
        
        return null;
    }

    //process a login
    public Employee processLogin(Employee employee){
        if(employee.getPassword() == employeeRepository.findEmployeeByUsernameAndPassword(employee.getUsername(), employee.getPassword()).getPassword())
            return employeeRepository.findEmployeeByUsernameAndPassword(employee.getUsername(), employee.getPassword());
        
        return null;
    }

    
}
