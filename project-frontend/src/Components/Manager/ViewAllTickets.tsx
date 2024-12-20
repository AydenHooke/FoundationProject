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


function ViewAllTickets() {
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
    <table>
      <thead>
        <th>Requested ID</th>
        <th>Ticket ID</th>
        <th>Amount</th>
        <th>Description</th>
        <th>Status</th>
      </thead>
      <tbody>
        {viewedTickets.map( ticket => (
          <tr key = {ticket.ticketId}>
            <td>{ticket.requestedId}</td>
            <td>{ticket.ticketId}</td>
            <td>{ticket.reimbursementAmount}</td>
            <td>{ticket.reimbursementDescription}</td>
            <td>{ticket.ticketStatus}</td>
          </tr>
        ))}
        
      </tbody>
    </table>
    
</>
    
  )
}


export default ViewAllTickets