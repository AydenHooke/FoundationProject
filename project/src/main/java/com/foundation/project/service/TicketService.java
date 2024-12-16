package com.foundation.project.service;

import com.foundation.project.entity.Employee;
import com.foundation.project.entity.Ticket.*;
import com.foundation.project.entity.Ticket;
import com.foundation.project.repository.TicketRepository;

import jakarta.transaction.Transactional;

import com.foundation.project.repository.EmployeeRepository;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TicketService {
    TicketRepository ticketRepository;
    EmployeeRepository employeeRepository;
    public TicketService(TicketRepository ticketRepository, EmployeeRepository employeeRepository){
        this.ticketRepository = ticketRepository;
        this.employeeRepository = employeeRepository;
    }

    //persist a ticket
    @Transactional
    public Ticket createTicket(Ticket ticket){
        if(ticket.getReimbursementAmount() > 0)
            if(ticket.getReimbursementDescription().length() > 0)
                return this.ticketRepository.save(ticket);
        
        return null;
    }

    @Transactional
    public Ticket approveTicket(Ticket ticket){
        if(ticket.getTicketId() == ticketRepository.findTicketByTicketId(ticket.getTicketId()).getTicketId())
            if(ticket.getTicketStatus() == status.PENDING){
                ticket.setTicketStatus(status.APPROVED);
                return this.ticketRepository.save(ticket);
            }
        // returns null upon ticket being NOT pending - not an exception
        return null;
    }

    @Transactional
    public Ticket denyTicket(Ticket ticket){
        if(ticket.getTicketId() == ticketRepository.findTicketByTicketId(ticket.getTicketId()).getTicketId())
            if(ticket.getTicketStatus() == status.PENDING){
                ticket.setTicketStatus(status.DENIED);
                return this.ticketRepository.save(ticket);
            }
        // returns null upon ticket being NOT pending - not an exception
        return null;
    }

    public List<Ticket> showMyTickets(Employee employee){
        if(employee.getEmployeeId() == employeeRepository.findEmployeeByEmployeeId(employee.getEmployeeId()).getEmployeeId())
            return this.ticketRepository.findTicketsByRequestedId(employee.getEmployeeId());
        
        return null;
    }

}
