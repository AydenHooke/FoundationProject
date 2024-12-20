package com.foundation.project.service;

import com.foundation.project.entity.Employee;
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
    public Ticket createTicket(Ticket ticket, int requestedId){
        if(ticket.getReimbursementAmount() > 0)
            if(ticket.getReimbursementDescription().length() > 0){
                ticket.setRequestedId(requestedId);
                ticket.setTicketStatus("PENDING");
                return this.ticketRepository.save(ticket);
            }
                
        return null;
    }

    @Transactional
    public Ticket approveTicket(Ticket ticket){
        if(this.ticketRepository.findTicketByTicketId(ticket.getTicketId()) != null)
            if(ticket.getTicketStatus().equals("PENDING")){
                ticket.setTicketStatus("APPROVED");
                return this.ticketRepository.save(ticket);
            }
        // returns null upon ticket being NOT pending - not an exception
        return null;
    }

    @Transactional
    public Ticket denyTicket(Ticket ticket){
        if(this.ticketRepository.findTicketByTicketId(ticket.getTicketId()) != null)
            if(ticket.getTicketStatus().equals("PENDING")){
                ticket.setTicketStatus("DENIED");
                return this.ticketRepository.save(ticket);
            }
        // returns null upon ticket being NOT pending - not an exception
        return null;
    }

    public Ticket findMyTicket(int ticketId){
        if(this.ticketRepository.findTicketByTicketId(ticketId) != null)
            return this.ticketRepository.findTicketByTicketId(ticketId);
        
        return null;
    }

    public List<Ticket> showMyTickets(Employee employee){
        if(this.employeeRepository.findEmployeeByEmployeeId(employee.getEmployeeId()) != null)
            return this.ticketRepository.findTicketsByRequestedId(employee.getEmployeeId());
        
        return null;
    }

    public List<Ticket> showAllTickets(Employee employee){
        if(employeeRepository.findEmployeeByEmployeeId(employee.getEmployeeId()) != null)
            return this.ticketRepository.findTicketsByTicketIdNotNull();
        
        return null;
    }

    public List<Ticket> findAllPendingTickets(Employee employee, String status){
        if(employeeRepository.findEmployeeByEmployeeId(employee.getEmployeeId()) != null)
            return this.ticketRepository.findTicketsByTicketStatus(status);
        
        return null;
    }

}
