package com.foundation.project.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="ticket")
@Data
public class Ticket {

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
    
    private String ticketStatus;
    /*
     * The status of the ticket
     */

    public void setTicketStatus(String pendingStatusOnly){
        if(this.ticketStatus == null)
            this.ticketStatus = "PENDING";
        if(this.ticketStatus.equals("PENDING"))     //this ensures that tickets cannot be modified after processing
            this.ticketStatus = pendingStatusOnly;
         
    }

    public Ticket(){

    }

    public Ticket(Integer reimbursementAmount, String reimbursementDescription){
        this.reimbursementAmount = reimbursementAmount;
        this.reimbursementDescription = reimbursementDescription;
    }

    public Ticket(Integer requestedId, Integer reimbursementAmount, String reimbursementDescription){
        this.requestedId = requestedId;
        this.reimbursementAmount = reimbursementAmount;
        this.reimbursementDescription = reimbursementDescription;
    }

    public Ticket(Integer ticketId, Integer requestedId, Integer reimbursementAmount, String reimbursementDescription){
        this.ticketId = ticketId;
        this.requestedId = requestedId;
        this.reimbursementAmount = reimbursementAmount;
        this.reimbursementDescription = reimbursementDescription;
    }
}
