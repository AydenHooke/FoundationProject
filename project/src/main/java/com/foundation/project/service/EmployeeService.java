package com.foundation.project.service;

import com.foundation.project.entity.Employee;
import com.foundation.project.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    //persist an employee
    @Transactional
    public Employee createEmployee(Employee employee){
        if(this.employeeRepository.findEmployeeByUsername(employee.getUsername()) == null) //checking if username is NOT registered, i.e. null
            if(employee.getUsername().length() > 0)
                if(employee.getPassword().length() > 0){
                    employee.setAccessLevel(0);
                    return this.employeeRepository.save(employee);
                }
        
        return null;
    }

    //process a login
    public Employee processLogin(Employee employee){
            if(this.employeeRepository.findEmployeeByUsernameAndPassword(employee.getUsername(), employee.getPassword())!=null)
                return this.employeeRepository.findEmployeeByUsernameAndPassword(employee.getUsername(), employee.getPassword());
            
            return null;
    }

    @Transactional
    public Employee promoteMe(Employee employee, int promotionCode){
        if(employee.getUsername().equals(this.employeeRepository.findEmployeeByUsername(employee.getUsername()).getUsername()) && promotionCode == 6969){
            employee = this.employeeRepository.findEmployeeByUsername(employee.getUsername());
            employee.setAccessLevel(employee.getAccessLevel()+1);
            return this.employeeRepository.save(employee);
        }

        return null;
    }

    public Employee findEmployeeById(int id){
        if(this.employeeRepository.findEmployeeByEmployeeId(id) != null)
            return this.employeeRepository.findEmployeeByEmployeeId(id);
        
        return null;
    }

}
