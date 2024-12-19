import React from 'react'
import { Link } from 'react-router-dom';

function NavBar() {
  return (
    <ul className="nav nav-pills">
  <li className="nav-item">
    <Link className="nav-link active" aria-current="page" to="/login">Login</Link>
  </li>
  <li className="nav-item">
    <Link className="nav-link" to="/submitTicket">Submit A Ticket</Link>
  </li>
  <li className="nav-item">
    <Link className="nav-link" to="/viewMyTickets">View My Tickets</Link>
  </li>
  <li className="nav-item">
  <Link className="nav-link" to="/processTickets">Process Tickets</Link>
  </li>
  <li className="nav-item">
  <Link className="nav-link" to="/promoteEmployee">Promote An Employee</Link>
  </li>
</ul>
  );
}

export default NavBar