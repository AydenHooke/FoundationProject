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
            else
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
      <table style = {{width: "100%"}}>
        <thead>
          <th style = {{color: "#00A36C", borderBottom: "1px solid #ddd"}}>Employee #</th>
          <th style = {{color: "#00A36C", borderBottom: "1px solid #ddd"}}>Ticket #</th>
          <th style = {{color: "#00A36C", borderBottom: "1px solid #ddd"}}>Amount</th>
          <th style = {{color: "#00A36C", borderBottom: "1px solid #ddd"}}>Description</th>
          <th style = {{color: "#00A36C", borderBottom: "1px solid #ddd"}}>Status</th>
          <th style = {{color: "#00A36C", borderBottom: "0px solid #ddd"}}>Approval</th>
        </thead>
        <tbody>
          {viewedTickets.map(ticket => (
            <tr key = {ticket.ticketId}>
              <td style = {{borderBottom: "1px solid #ddd"}}>{ticket.requestedId}</td>
              <td style = {{borderBottom: "1px solid #ddd"}}>{ticket.ticketId}</td>
              <td style = {{borderBottom: "1px solid #ddd"}}>${ticket.reimbursementAmount}</td>
              <td style = {{borderBottom: "1px solid #ddd"}}>{ticket.reimbursementDescription}</td>
              <td style = {{borderBottom: "1px solid #ddd"}}>{ticket.ticketStatus}</td>
              <button onClick = {(e) => approveTicket(ticket)} style = {{backgroundColor : "#00FF00", borderBottom: "1px solid #ddd", width:"33px", height:"11px", margin: "3px"}} value="Submit"></button>
              <button onClick = {(e) => denyTicket(ticket)} style = {{backgroundColor : "#FF0000", borderBottom: "1px solid #ddd", width:"33px", height:"11px", margin: "3px"}} value="Submit"></button>
            </tr>
          ))}
          
        </tbody>
      </table>
      
  </>
      
    )
}

export default ProcessTickets