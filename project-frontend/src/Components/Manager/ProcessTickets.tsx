import React, { useContext, useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { userContext } from '../../App';

type TicketData = {
  ticketId: number,
  requestedId: string,
  reimbursementAmount: number,
  reimbursementDescription: number,
  ticketStatus: string
}

function ProcessTickets() {
  const [viewedTickets, setDemTickets] = useState<Array<TicketData>>([]);
    const currentEmployee = useContext(userContext)
    const navigateMe = useNavigate();
  
    useEffect(()=>
        {
            if(currentEmployee.powerLevel < 0)
                navigateMe('/', {})
  
            getTickets();
      }, [])
  

    async function getTickets() {
      const userTicket = await fetch(`http://localhost:8080/status?employeeId=${currentEmployee.employeeId}`);
      if(userTicket.status == 200){
        let ticketsAgain = await userTicket.json();
        setDemTickets(ticketsAgain);
      }
      else
        window.alert("There was an error submitting your request");
    }
  
    async function approveTicket(ticket : TicketData){
      const userTicket = await fetch(`http://localhost:8080/approveTicket?ticketId=${ticket.ticketId}&employeeId=${currentEmployee.employeeId}`, {
        method: 'POST',
      })   

      try{
        let stateOfTicket = await userTicket.json(); //intentional
      }catch(e){}

      if(userTicket.status != 200)
        window.alert("There was an error approving your ticket");
      
      getTickets();
    }

    async function denyTicket(ticket : TicketData){
      const userTicket = await fetch(`http://localhost:8080/denyTicket?ticketId=${ticket.ticketId}&employeeId=${currentEmployee.employeeId}`, {
        method: 'POST',
      })   

      try{
        let stateOfTicket = await userTicket.json(); //intentional
      }catch(e){}

      if(userTicket.status != 200)
        window.alert("There was an error approving your ticket");

      getTickets();
    }
    


    return (
  <>
      <table>
        <thead>
          <th>Requested ID</th>
          <th>Ticket ID</th>
          <th>Amount</th>
          <th>Description</th>
          <th>Status</th>
          <th>Approval</th>
        </thead>
        <tbody>
          {viewedTickets.map(ticket => (
            <tr key = {ticket.ticketId}>
              <td>{ticket.requestedId}</td>
              <td>{ticket.ticketId}</td>
              <td>${ticket.reimbursementAmount}</td>
              <td>{ticket.reimbursementDescription}</td>
              <td>{ticket.ticketStatus}</td>
              <button onClick = {(e) => approveTicket(ticket)} style = {{color : "green"}} value="Submit"></button>
              <button onClick = {(e) => denyTicket(ticket)} style = {{color : "red"}} value="Submit"></button>
            </tr>
          ))}
          
        </tbody>
      </table>
      
  </>
      
    )
}

export default ProcessTickets