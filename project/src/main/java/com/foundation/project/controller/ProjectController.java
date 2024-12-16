package com.foundation.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.foundation.project.entity.*;
import com.foundation.project.service.*;

@RestController
public class ProjectController {
    EmployeeService employeeService;
    TicketService ticketService;

    public ProjectController(EmployeeService employeeService, TicketService ticketService){
        this.employeeService = employeeService;
        this.ticketService = ticketService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrationHandler(@RequestBody Employee newEmployee){
        Employee testEmployee = employeeService.createEmployee(newEmployee);
        if(testEmployee != null)
            return ResponseEntity.status(HttpStatus.OK)
                .body(testEmployee);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .build();
    }

    @PostMapping("/promote")
    public ResponseEntity<?> promotionHandler(@RequestBody Employee employee, @RequestParam int code){
        Employee promotedEmployee = employeeService.promoteMe(employee, code);
        if(promotedEmployee != null)
            return ResponseEntity.status(HttpStatus.OK)
                .body(promotedEmployee);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .build();
    }
    

}
