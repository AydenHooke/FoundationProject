package com.foundation.project.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="ticket")
@Data
public class Ticket {
    
    public enum status{
        PENDING,
        APPROVED,
        DENIED
    }

    @Column(name="ticketId")
    @Id
    @GeneratedValue

    private Integer ticketId;
    /*
     * The ID for the ticket
     */
    private Integer requestedId;
    /*
     * This is the ID of the employee that requested the refund
     */
    private Integer reimbursementAmount;
    /*
     * This is the amount of the reimbursement
     */
    private String reimbursementDescription;
    /*
     * This a description of the reimbursement
     */
    @Enumerated(EnumType.STRING)
    private status ticketStatus;
    /*
     * The status of the ticket
     */

    public void setTicketStatus(status pendingStatusOnly){
        if(this.ticketStatus == status.PENDING) //this ensures that tickets cannot be modified after processing
            this.ticketStatus = pendingStatusOnly;
    }

    public Ticket(){

    }

    public Ticket(Integer reimbursementAmount, String reimbursementDescription){
        this.requestedId = -1;
        this.reimbursementAmount = reimbursementAmount;
        this.reimbursementDescription = reimbursementDescription;
        this.ticketStatus = status.PENDING;
    }

    public Ticket(Integer requestedId, Integer reimbursementAmount, String reimbursementDescription){
        this.requestedId = requestedId;
        this.reimbursementAmount = reimbursementAmount;
        this.reimbursementDescription = reimbursementDescription;
        this.ticketStatus = status.PENDING;
    }

    public Ticket(Integer ticketId, Integer requestedId, Integer reimbursementAmount, String reimbursementDescription){
        this.ticketId = ticketId;
        this.requestedId = requestedId;
        this.reimbursementAmount = reimbursementAmount;
        this.reimbursementDescription = reimbursementDescription;
        this.ticketStatus = status.PENDING;
    }

}
