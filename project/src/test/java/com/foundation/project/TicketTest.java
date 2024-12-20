package com.foundation.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.foundation.project.entity.Ticket;

public class TicketTest {
    @Test
    void testTicketConstructor_NoArgs() {
        // Create ticket using no-argument constructor
        Ticket ticket = new Ticket();

        // Verify the initial state of the ticket
        assertNull(ticket.getTicketId());
        assertNull(ticket.getRequestedId());
        assertNull(ticket.getReimbursementAmount());
        assertNull(ticket.getReimbursementDescription());
        assertNull(ticket.getTicketStatus());
    }

    @Test
    void testTicketConstructor_ReimbursementAmountAndDescription() {
        // Create ticket with reimbursement amount and description
        Ticket ticket = new Ticket(100, "Office Supplies");

        // Verify the state of the ticket
        assertNull(ticket.getTicketId()); // ID is not set
        assertNull(ticket.getRequestedId()); // Requested ID is not set
        assertEquals(100, ticket.getReimbursementAmount());
        assertEquals("Office Supplies", ticket.getReimbursementDescription());
        assertNull(ticket.getTicketStatus()); // Status is not set yet
    }

    @Test
    void testTicketConstructor_RequestId_ReimbursementAmountAndDescription() {
        // Create ticket with requested ID, reimbursement amount, and description
        Ticket ticket = new Ticket(1, 200, "Travel Expenses");

        // Verify the state of the ticket
        assertNull(ticket.getTicketId()); // ID is not set
        assertEquals(1, ticket.getRequestedId());
        assertEquals(200, ticket.getReimbursementAmount());
        assertEquals("Travel Expenses", ticket.getReimbursementDescription());
        assertNull(ticket.getTicketStatus()); // Status is not set yet
    }

    @Test
    void testTicketConstructor_AllArgs() {
        // Create ticket with all fields
        Ticket ticket = new Ticket(1, 1, 300, "Medical Expenses");

        // Verify the state of the ticket
        assertEquals(1, ticket.getTicketId());
        assertEquals(1, ticket.getRequestedId());
        assertEquals(300, ticket.getReimbursementAmount());
        assertEquals("Medical Expenses", ticket.getReimbursementDescription());
        assertNull(ticket.getTicketStatus()); // Status is not set yet
    }

    @Test
    void testSetTicketStatus_InitialNullStatus() {
        // Create a ticket with null status
        Ticket ticket = new Ticket();
        assertNull(ticket.getTicketStatus());

        // Set status and check if it gets set to PENDING
        ticket.setTicketStatus("APPROVED");
        assertEquals("APPROVED", ticket.getTicketStatus());
    }

    @Test
    void testSetTicketStatus_PendingStatus() {
        // Create a ticket with a PENDING status
        Ticket ticket = new Ticket();
        ticket.setTicketStatus("PENDING");

        // Attempt to update status
        ticket.setTicketStatus("APPROVED");

        // Verify that the status is not updated after it's set to "PENDING"
        assertEquals("APPROVED", ticket.getTicketStatus());
    }

    @Test
    void testSetTicketStatus_WhenAlreadySet() {
        // Create a ticket with an initial status
        Ticket ticket = new Ticket();
        ticket.setTicketStatus("PENDING");

        // Set the status again to "DENIED"
        ticket.setTicketStatus("DENIED");

        // Verify that the status was updated
        assertEquals("DENIED", ticket.getTicketStatus());
    }

    @Test
    void testTicketWithNullValues() {
        // Create ticket with all values null or zero
        Ticket ticket = new Ticket(null, null, null, null);

        // Verify the state of the ticket
        assertNull(ticket.getTicketId());
        assertNull(ticket.getRequestedId());
        assertNull(ticket.getReimbursementAmount());
        assertNull(ticket.getReimbursementDescription());
        assertNull(ticket.getTicketStatus());
    }

    @Test
    void testTicketWithEmptyDescription() {
        // Create ticket with empty reimbursement description
        Ticket ticket = new Ticket(1, 100, "");

        // Verify the state of the ticket
        assertEquals(1, ticket.getRequestedId());
        assertEquals(100, ticket.getReimbursementAmount());
        assertEquals("", ticket.getReimbursementDescription());
        assertNull(ticket.getTicketStatus());
    }

    @Test
    void testTicketWithNullAmount() {
        // Create ticket with null reimbursement amount
        Ticket ticket = new Ticket(1, null, "Miscellaneous");

        // Verify the state of the ticket
        assertEquals(1, ticket.getRequestedId());
        assertNull(ticket.getReimbursementAmount());
        assertEquals("Miscellaneous", ticket.getReimbursementDescription());
        assertNull(ticket.getTicketStatus());
}

    @Test
    void testToString() {
        // Create a ticket
        Ticket ticket = new Ticket(1, 1, 100, "Travel Expenses");

        // Check if the toString method is working as expected
        String expectedString = "Ticket(ticketId=1, requestedId=1, reimbursementAmount=100, reimbursementDescription=Travel Expenses, ticketStatus=null)";
        assertEquals(expectedString, ticket.toString());
    }

    @Test
    void testTicketStatusTransitions() {
        // Create ticket with default status (should be PENDING)
        Ticket ticket = new Ticket(1, 100, "Travel Expenses");
        Ticket ticket2 = new Ticket(1, 100, "Travel Expenses");
        
        // Verify initial status
        assertNull(ticket.getTicketStatus()); // Initially null, so let's set it to PENDING
        ticket.setTicketStatus("PENDING");
        assertEquals("PENDING", ticket.getTicketStatus());

        // Change status to "APPROVED"
        ticket.setTicketStatus("APPROVED");
        assertEquals("APPROVED", ticket.getTicketStatus());

        // Change status to "DENIED"
        ticket2.setTicketStatus("DENIED");
        assertEquals("DENIED", ticket2.getTicketStatus());
    }
}
