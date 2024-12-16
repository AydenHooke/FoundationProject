package com.foundation.project.repository;

import java.util.List;

import jakarta.transaction.Transactional;

import com.foundation.project.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{
    
    //create ticket through .save

    List<Ticket> findTicketsByTicketIdNotNull(); //returns all tickets

    Ticket findTicketByTicketId(int ticketId);

    List<Ticket> findTicketsByStatus(String status);
    
    List<Ticket> findTicketsByRequestedId(int requestedId);

}
