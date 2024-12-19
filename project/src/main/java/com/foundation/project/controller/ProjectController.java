package com.foundation.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.micrometer.core.ipc.http.HttpSender.Response;

@RestController
@CrossOrigin
public class ProjectController {
    EmployeeService employeeService;
    TicketService ticketService;

    public ProjectController(EmployeeService employeeService, TicketService ticketService){
        this.employeeService = employeeService;
        this.ticketService = ticketService;
    }

    @PostMapping("/accountService")
    public ResponseEntity<?> registrationHandler(@RequestBody Employee newEmployee){
        Employee testEmployee = employeeService.processLogin(newEmployee);
        if(testEmployee != null){
            System.out.println("User: " + testEmployee.getEmployeeId() + " has logged in"); // checking to login
            return ResponseEntity.status(HttpStatus.OK)
                .build(); 
            }

        testEmployee = employeeService.createEmployee(newEmployee);

        if(testEmployee != null){                              // creating account if valid
            System.out.println("User: " + testEmployee.getEmployeeId() + " has been created");
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(testEmployee);
        }
                
        return ResponseEntity.status(HttpStatus.CONFLICT)     // if error or not valid
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
    
    @GetMapping("/tickets")
    public ResponseEntity<?> returnMyTicketsHandler(@RequestParam int id){
        Employee persistedCheck = employeeService.findEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK)
            .body(ticketService.showMyTickets(persistedCheck));
    }
}
