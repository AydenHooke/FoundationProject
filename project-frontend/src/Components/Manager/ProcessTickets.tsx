import React, { useContext, useEffect } from 'react'
import { useNavigate } from 'react-router-dom';
import { userContext } from '../../App';

function ProcessTickets() {
  const currentEmployee = useContext(userContext)
    const navigateMe = useNavigate();

    useEffect(()=>
        {
            if(currentEmployee.powerLevel < 0)
                navigateMe('/', {})
      }, [])
  return (
    <div>Pfgsdfgsdfgsdfg</div>
  )
}

export default ProcessTickets