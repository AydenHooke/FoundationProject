import React, { useContext, useEffect } from 'react'
import { Link, Outlet, useNavigate } from 'react-router-dom'
import { userContext } from '../../App';



function EmployeeWelcome({}) {
    const currentEmployee = useContext(userContext)
    const navigateMe = useNavigate();

    useEffect(()=>
      {
        if(currentEmployee.powerLevel < 0)
            navigateMe('/', {})
      }, [])

  return (
    <>
<ul className="nav nav-pills">
  <li className="nav-item">
    <Link className="nav-link active" aria-current="page" to="/">Logout</Link>
  </li>
  <li className="nav-item">
    <Link className="nav-link" to="/home/submitTicket">Submit A Ticket</Link>
  </li>
  <li className="nav-item">
    <Link className="nav-link" to="/home/viewMyTickets">View My Tickets</Link>
  </li>
</ul>

<br></br>

    <h1 style = {{color: "#FC6C85"}}>{"Welcome " + currentEmployee.username + "!"}</h1>

    <Outlet/>


    </>

  
  )
}

export default EmployeeWelcome