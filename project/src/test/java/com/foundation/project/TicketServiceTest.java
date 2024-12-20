package com.foundation.project;

import com.foundation.project.entity.Employee;
import com.foundation.project.entity.Ticket;
import com.foundation.project.repository.EmployeeRepository;
import com.foundation.project.repository.TicketRepository;
import com.foundation.project.service.TicketService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private TicketService ticketService;

    private Ticket testTicket;
    private Employee testEmployee;

    @BeforeEach
    void setUp() {
        // Setup a sample ticket and employee for testing
        testEmployee = new Employee("testUser", "testPassword", 0);
        testTicket = new Ticket(100, "Test Description");
        testTicket.setTicketId(1);
        testTicket.setRequestedId(1);
    }

    @Test
    void testApproveTicket_Success() {
        // Mock the behavior of the repository methods
        when(ticketRepository.findTicketByTicketId(testTicket.getTicketId())).thenReturn(testTicket);
        when(ticketRepository.save(testTicket)).thenReturn(testTicket);

        // Set ticket status to "PENDING"
        testTicket.setTicketStatus("PENDING");

        // Call the approveTicket method
        Ticket approvedTicket = ticketService.approveTicket(testTicket);

        // Assertions
        assertNotNull(approvedTicket);
        assertEquals("APPROVED", approvedTicket.getTicketStatus());
    }

    @Test
    void testApproveTicket_TicketNotFound() {
        // Mock the behavior of the repository methods
        when(ticketRepository.findTicketByTicketId(testTicket.getTicketId())).thenReturn(null);

        // Call the approveTicket method
        Ticket approvedTicket = ticketService.approveTicket(testTicket);

        // Assertions
        assertNull(approvedTicket); // Should return null as the ticket does not exist
    }

    @Test
    void testApproveTicket_NotPending() {
        // Mock the behavior of the repository methods
        when(ticketRepository.findTicketByTicketId(testTicket.getTicketId())).thenReturn(testTicket);

        // Set ticket status to "APPROVED"
        testTicket.setTicketStatus("APPROVED");

        // Call the approveTicket method
        Ticket approvedTicket = ticketService.approveTicket(testTicket);

        // Assertions
        assertNull(approvedTicket); // Should return null as the ticket status is not "PENDING"
    }

    @Test
    void testDenyTicket_Success() {
        // Mock the behavior of the repository methods
        when(ticketRepository.findTicketByTicketId(testTicket.getTicketId())).thenReturn(testTicket);
        when(ticketRepository.save(testTicket)).thenReturn(testTicket);

        // Set ticket status to "PENDING"
        testTicket.setTicketStatus("PENDING");

        // Call the denyTicket method
        Ticket deniedTicket = ticketService.denyTicket(testTicket);

        // Assertions
        assertNotNull(deniedTicket);
        assertEquals("DENIED", deniedTicket.getTicketStatus());
    }

    @Test
    void testDenyTicket_NotPending() {
        // Mock the behavior of the repository methods
        when(ticketRepository.findTicketByTicketId(testTicket.getTicketId())).thenReturn(testTicket);

        // Set ticket status to "APPROVED"
        testTicket.setTicketStatus("APPROVED");

        // Call the denyTicket method
        Ticket deniedTicket = ticketService.denyTicket(testTicket);

        // Assertions
        assertNull(deniedTicket); // Should return null as the ticket status is not "PENDING"
    }

    @Test
    void testFindMyTicket_Success() {
        // Mock the behavior of the repository methods
        when(ticketRepository.findTicketByTicketId(testTicket.getTicketId())).thenReturn(testTicket);

        // Call the findMyTicket method
        Ticket foundTicket = ticketService.findMyTicket(testTicket.getTicketId());

        // Assertions
        assertNotNull(foundTicket);
        assertEquals(testTicket.getTicketId(), foundTicket.getTicketId());
    }

    @Test
    void testFindMyTicket_Failure() {
        // Mock the behavior of the repository methods
        when(ticketRepository.findTicketByTicketId(testTicket.getTicketId())).thenReturn(null);

        // Call the findMyTicket method
        Ticket foundTicket = ticketService.findMyTicket(testTicket.getTicketId());

        // Assertions
        assertNull(foundTicket); // Should return null as the ticket does not exist
    }

}