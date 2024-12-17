import React from 'react'
import { Link } from 'react-router-dom';

function NavBar() {
  return (
    <ul className="nav nav-pills">
  <li className="nav-item">
    <Link className="nav-link active" aria-current="page" to="/login">Get Authenticated!</Link>
  </li>
  <li className="nav-item">
    <Link className="nav-link" to="/events">View Pending Requests</Link>
  </li>
  <li className="nav-item">
    <Link className="nav-link" to="/something-else">Submit A Request</Link>
  </li>
  <li className="nav-item">
  <Link className="nav-link" to="/promoteMe">Get Promoted!</Link>
  </li>
  <li className="nav-item">
  <Link className="nav-link" to="/hooks">Hooks</Link>
  </li>
</ul>
  );
}

export default NavBar