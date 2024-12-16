package com.foundation.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.foundation.project.entity.*;
import com.foundation.project.service.*;

@RestController
public class ProjectController {
    EmployeeService employeeService;
    TicketService ticketService;

    @Autowired
    public ProjectController(EmployeeService employeeService, TicketService ticketService){
        this.employeeService = employeeService;
        this.ticketService = ticketService;
    }





}
