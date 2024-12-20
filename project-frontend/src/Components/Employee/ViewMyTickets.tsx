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
      })
  return (
    <div>viewMyTickets</div>
  )
}

export default ViewMyTickets