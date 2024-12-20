package com.foundation.project;

import com.foundation.project.controller.ProjectController;
import com.foundation.project.entity.Employee;
import com.foundation.project.entity.Ticket;
import com.foundation.project.service.EmployeeService;
import com.foundation.project.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TryTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrationHandler_Success() {
        Employee newEmployee = new Employee();
        newEmployee.setEmployeeId(1); // Example employee data
        
        // Mock the behavior of the employee service
        when(employeeService.processLogin(newEmployee)).thenReturn(newEmployee);
        when(employeeService.createEmployee(newEmployee)).thenReturn(null); // Mock account creation failure
        
        // Call the method
        ResponseEntity<?> response = projectController.registrationHandler(newEmployee);

        // Verify the response status
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(employeeService, times(1)).processLogin(newEmployee);
        verify(employeeService, times(0)).createEmployee(newEmployee);
    }

    @Test
    void testPromotionHandler_Success() {
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setAccessLevel(1); // Promote the employee

        // Mock the behavior of the employee service
        when(employeeService.promoteMe(employee, 123)).thenReturn(employee);

        // Call the method
        ResponseEntity<?> response = projectController.promotionHandler(employee, 123);

        // Verify the response status
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(employeeService, times(1)).promoteMe(employee, 123);
    }

    @Test
    void testCreateTicket_Success() {
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        Ticket ticket = new Ticket();
        ticket.setTicketId(1);

        // Mock the behavior of the services
        when(employeeService.findEmployeeById(1)).thenReturn(employee);
        when(ticketService.createTicket(ticket, 1)).thenReturn(ticket);

        // Call the method
        ResponseEntity<?> response = projectController.createMyTicket(ticket, 1);

        // Verify the response status
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(ticketService, times(1)).createTicket(ticket, 1);
    }

    @Test
    void testCreateTicket_EmployeeNotFound() {
        Ticket ticket = new Ticket();

        // Mock the behavior of the services
        when(employeeService.findEmployeeById(1)).thenReturn(null);

        // Call the method
        ResponseEntity<?> response = projectController.createMyTicket(ticket, 1);

        // Verify the response status
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void testShowTickets_Success() {
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setAccessLevel(1);

        // Mock the behavior of the service
        when(employeeService.findEmployeeById(1)).thenReturn(employee);
        when(ticketService.showAllTickets(employee)).thenReturn(List.of(new Ticket()));

        // Call the method
        ResponseEntity<?> response = projectController.showTickets(1);

        // Verify the response status
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(ticketService, times(1)).showAllTickets(employee);
    }

    @Test
    void testShowTickets_InsufficientAccess() {
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setAccessLevel(0); // Regular employee

        // Mock the behavior of the service
        when(employeeService.findEmployeeById(1)).thenReturn(employee);
        when(ticketService.showMyTickets(employee)).thenReturn(List.of(new Ticket()));

        // Call the method
        ResponseEntity<?> response = projectController.showTickets(1);

        // Verify the response status
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(ticketService, times(1)).showMyTickets(employee);
    }

    @Test
void testApproveTicket_Success() {
    Employee employee = new Employee();
    employee.setEmployeeId(1);
    employee.setAccessLevel(2); // Admin level access

    Ticket ticket = new Ticket();
    ticket.setTicketId(1);
    
    // Mock the behavior of the services
    when(employeeService.findEmployeeById(1)).thenReturn(employee);
    when(ticketService.findMyTicket(1)).thenReturn(ticket);
    
    // Call the method
    ResponseEntity<?> response = projectController.approveTicket(1, 1);
    
    // Verify the response status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(ticketService, times(1)).approveTicket(ticket);
}

@Test
void testApproveTicket_Unauthorized() {
    Employee employee = new Employee();
    employee.setEmployeeId(1);
    employee.setAccessLevel(0); // Regular employee, no approval access

    Ticket ticket = new Ticket();
    ticket.setTicketId(1);
    
    // Mock the behavior of the services
    when(employeeService.findEmployeeById(1)).thenReturn(employee);
    when(ticketService.findMyTicket(1)).thenReturn(ticket);
    
    // Call the method
    ResponseEntity<?> response = projectController.approveTicket(1, employee.getAccessLevel());
    
    // Verify the response status
    assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    verify(ticketService, times(0)).approveTicket(ticket);
}

@Test
void testDenyTicket_Success() {
    Employee employee = new Employee();
    employee.setEmployeeId(1);
    employee.setAccessLevel(2); // Admin level access

    Ticket ticket = new Ticket();
    ticket.setTicketId(1);

    // Mock the behavior of the services
    when(employeeService.findEmployeeById(1)).thenReturn(employee);
    when(ticketService.findMyTicket(1)).thenReturn(ticket);
    
    // Call the method
    ResponseEntity<?> response = projectController.denyTicket(1, 1);
    
    // Verify the response status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(ticketService, times(1)).denyTicket(ticket);
}

@Test
void testDenyTicket_Unauthorized() {
    Employee employee = new Employee();
    employee.setEmployeeId(1);
    employee.setAccessLevel(0); // Regular employee, no deny access

    Ticket ticket = new Ticket();
    ticket.setTicketId(1);

    // Mock the behavior of the services
    when(employeeService.findEmployeeById(1)).thenReturn(employee);
    when(ticketService.findMyTicket(1)).thenReturn(ticket);
    
    // Call the method
    ResponseEntity<?> response = projectController.denyTicket(1, employee.getAccessLevel());
    
    // Verify the response status
    assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    verify(ticketService, times(0)).denyTicket(ticket);
}

@Test
void testShowPendingTickets_Success() {
    Employee employee = new Employee();
    employee.setEmployeeId(1);
    employee.setAccessLevel(2); // Admin level access

    Ticket pendingTicket = new Ticket();
    pendingTicket.setTicketId(1);

    // Mock the behavior of the services
    when(employeeService.findEmployeeById(1)).thenReturn(employee);
    when(ticketService.findAllPendingTickets(employee, "PENDING")).thenReturn(List.of(pendingTicket));
    
    // Call the method
    ResponseEntity<?> response = projectController.showPendingTickets(1);
    
    // Verify the response status
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    verify(ticketService, times(1)).findAllPendingTickets(employee, "PENDING");
}

@Test
void testShowPendingTickets_InsufficientAccess() {
    Employee employee = new Employee();
    employee.setEmployeeId(1);
    employee.setAccessLevel(0); // Regular employee, no admin access

    // Mock the behavior of the service
    when(employeeService.findEmployeeById(1)).thenReturn(employee);

    // Call the method
    ResponseEntity<?> response = projectController.showPendingTickets(1);
    
    // Verify the response status
    assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    verify(ticketService, times(0)).findAllPendingTickets(employee, "PENDING");
}

@Test
void testShowPendingTickets_EmployeeNotFound() {
    // Mock the behavior of the service
    when(employeeService.findEmployeeById(1)).thenReturn(null);

    // Call the method
    ResponseEntity<?> response = projectController.showPendingTickets(1);
    
    // Verify the response status
    assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    verify(ticketService, times(0)).findAllPendingTickets(any(), any());
}

@Test
void testCreateTicket_TicketAlreadyExists() {
    Employee employee = new Employee();
    employee.setEmployeeId(1);
    Ticket existingTicket = new Ticket();
    existingTicket.setTicketId(1);
    
    // Mock the behavior of the services
    when(employeeService.findEmployeeById(1)).thenReturn(employee);
    when(ticketService.createTicket(existingTicket, 1)).thenReturn(null); // Mock failure in ticket creation
    
    // Call the method
    ResponseEntity<?> response = projectController.createMyTicket(existingTicket, 1);
    
    // Verify the response status
    assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    verify(ticketService, times(1)).createTicket(existingTicket, 1);
}

@Test
void testRegistrationHandler_InvalidEmployee() {
    Employee newEmployee = new Employee();
    newEmployee.setEmployeeId(0); // Invalid employee ID
    
    // Mock the behavior of the employee service
    when(employeeService.processLogin(newEmployee)).thenReturn(null);
    when(employeeService.createEmployee(newEmployee)).thenReturn(null); // Simulate failure during account creation

    // Call the method
    ResponseEntity<?> response = projectController.registrationHandler(newEmployee);
    
    // Verify the response status
    assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    verify(employeeService, times(1)).processLogin(newEmployee);
    verify(employeeService, times(1)).createEmployee(newEmployee);
}
}