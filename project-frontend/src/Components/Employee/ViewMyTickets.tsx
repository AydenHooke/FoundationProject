import React, { useContext, useEffect } from 'react'
import { useNavigate } from 'react-router-dom';
import { userContext } from '../../App';

function ViewMyTickets() {
  const currentEmployee = useContext(userContext)
    const navigateMe = useNavigate();

    useEffect(()=>
        {
            if(currentEmployee.powerLevel < 0)
                navigateMe('/', {})

            getTickets();
            displayTickets();
      }, [])

    let viewedTickets: any[];

    async function getTickets() {
      const userTickets = await fetch(`http://localhost:8080/showTickets?employeeId=${currentEmployee.employeeId}`)

      if(userTickets.status == 200){
        viewedTickets = await userTickets.json();
        console.log(viewedTickets);
      }
      else
        window.alert("There was an error submitting your request");
    }

    function displayTickets(){
      return(
        <div>
      <table>
        <thead>
          <tr>Ticket ID</tr>
        </thead>
        <tbody>
          {viewedTickets.map((ticket) => (
            <td key={ticket.ticketId}></td>
          ))}
        </tbody>
      </table>
    </div>
      )
    }
    

    

  return (
    <>
    </>
    
  )
}

export default ViewMyTickets