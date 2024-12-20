package com.foundation.project.controller;

import java.util.List;

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
            System.out.println("Employee #" + testEmployee.getEmployeeId() + " has logged in"); // checking to login
            return ResponseEntity.status(HttpStatus.OK)
                .body(testEmployee); 
            }

        testEmployee = employeeService.createEmployee(newEmployee);

        if(testEmployee != null){                              // creating account if valid
            System.out.println("Employee #" + testEmployee.getEmployeeId() + " has been created");
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(testEmployee);
        }

        System.out.println("An error has occurred while servicing an account");
        return ResponseEntity.status(HttpStatus.CONFLICT)     // if error or not valid
                .build();
    }

    @PostMapping("/promote")
    public ResponseEntity<?> promotionHandler(@RequestBody Employee employee, @RequestParam int code){
        Employee promotedEmployee = employeeService.promoteMe(employee, code);
        if(promotedEmployee != null){
            System.out.println("Employee #" + promotedEmployee.getEmployeeId() + " has been promoted to level " + promotedEmployee.getAccessLevel() + " access");
            return ResponseEntity.status(HttpStatus.OK)
                .body(promotedEmployee);
        }///needs approval for promoting employees

        System.out.println("An error has occurred while promoting an employee");
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .build();
    }
    
    @PostMapping("/ticketCreation")
    public ResponseEntity<?> createMyTicket(@RequestBody Ticket ticket, @RequestParam int employeeId){
        Employee authorizationCheck = employeeService.findEmployeeById(employeeId);
        Ticket createdTicket = null;
        if(authorizationCheck != null)
            createdTicket = ticketService.createTicket(ticket, employeeId);

        if(createdTicket != null && authorizationCheck != null){
            System.out.println("A ticket has been created by employee #" + authorizationCheck.getEmployeeId());
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdTicket);
        }

        System.out.println("An error has occurred while creating a ticket"); 
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .build();
    }

    @GetMapping("/showTickets")
    public ResponseEntity<?> showTickets(@RequestParam int employeeId){
        Employee testEmployee = employeeService.findEmployeeById(employeeId);
        if(testEmployee !=null){
            if(testEmployee.getAccessLevel() > 0){
                List<Ticket> allTickets = ticketService.showAllTickets(testEmployee);
                System.out.println("Employee #" + employeeId + "has retrieved all tickets");
                return ResponseEntity.status(HttpStatus.OK)
                    .body(allTickets);
            }

            if(testEmployee.getAccessLevel() == 0){
                List<Ticket> employeeTickets = ticketService.showMyTickets(testEmployee);
                System.out.println("Employee #" + employeeId + "viewed their tickets");
                return ResponseEntity.status(HttpStatus.OK)
                    .body(employeeTickets);
            }
        }
        System.out.println("An error has occurred while attempting to view tickets"); 
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .build();
    }

    @PostMapping("/approveTicket")
    public ResponseEntity<?> approveTicket(@RequestParam int ticketId, @RequestParam int employeeId){
        Employee testEmployee = employeeService.findEmployeeById(employeeId);
        Ticket approvedTicket = ticketService.findMyTicket(ticketId);
        if(testEmployee !=null && approvedTicket != null){
            if(testEmployee.getAccessLevel() > 0)
                ticketService.approveTicket(approvedTicket);
                System.out.println("Employee #" + employeeId + "has approved ticket #" + ticketId);
                return ResponseEntity.status(HttpStatus.OK)
                    .build();
            }

        System.out.println("An error has occurred while attempting to approve a ticket"); 
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .build();
    }

    @PostMapping("/denyTicket")
    public ResponseEntity<?> denyTicket(@RequestParam int ticketId, @RequestParam int employeeId){
        Employee testEmployee = employeeService.findEmployeeById(employeeId);
        Ticket deniedTicket = ticketService.findMyTicket(ticketId);
        if(testEmployee !=null && deniedTicket != null){
            if(testEmployee.getAccessLevel() > 0)
                ticketService.denyTicket(deniedTicket);
                System.out.println("Employee #" + employeeId + "has denied ticket #" + ticketId);
                return ResponseEntity.status(HttpStatus.OK)
                    .build();
            }

        System.out.println("An error has occurred while attempting to approve a ticket"); 
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .build();
    }

     
}
