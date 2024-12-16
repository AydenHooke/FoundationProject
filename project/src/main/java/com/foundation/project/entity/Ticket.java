package com.foundation.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

enum status{
    PENDING,
    APPROVED,
    DENIED
}

@Entity
@Table(name="ticket")

public class Ticket {
    
    @Column(name="ticketId")
    @Id
    @GeneratedValue
    @Getter
    @Setter

    private Integer ticketId;
    /*
     * The ID for the ticket
     */
    private Integer requestedId;
    /*
     * This is the ID of the employee that requested the refund
     */
    private status ticketStatus;
    /*
     * The status of the ticket
     */
    public Ticket(){

    }

    public Ticket(Integer requestedId){
        this.requestedId = requestedId;
        this.ticketStatus = status.PENDING;
    }

    public Ticket(Integer requestedId, status ticketStatus){
        this.requestedId = requestedId;
        this.ticketStatus = ticketStatus;
    }

}
