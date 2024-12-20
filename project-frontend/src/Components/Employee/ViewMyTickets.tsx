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

function ViewMyTickets() {
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
    const userTickets = await fetch(`http://localhost:8080/showTickets?employeeId=${currentEmployee.employeeId}`)
    if(userTickets.status == 200){
      let ticketsAgain = await userTickets.json();
      setDemTickets(ticketsAgain);
    }
    else
      window.alert("There was an error submitting your request");
  }

  
    

    

  return (
<>
    <table style = {{width: "100%", textAlign: "center"}}>
      <thead>
        <th style = {{color: "#00A36C", borderBottom: "1px solid #ddd"}}>Ticket #</th>
        <th style = {{color: "#00A36C", borderBottom: "1px solid #ddd"}}>Amount</th>
        <th style = {{color: "#00A36C", borderBottom: "1px solid #ddd"}}>Description</th>
        <th style = {{color: "#00A36C", borderBottom: "1px solid #ddd"}}>Status</th>
      </thead>
      <tbody>
        {viewedTickets.map( ticket => (
          <tr key = {ticket.ticketId}>
            <td style = {{borderBottom: "1px solid #ddd"}}>{ticket.ticketId}</td>
            <td style = {{borderBottom: "1px solid #ddd"}}>${ticket.reimbursementAmount}</td>
            <td style = {{borderBottom: "1px solid #ddd"}}>{ticket.reimbursementDescription}</td>
            <td style = {{borderBottom: "1px solid #ddd"}}>{ticket.ticketStatus}</td>
          </tr>
        ))}
        
      </tbody>
    </table>
    
</>
    
  )
}

export default ViewMyTickets