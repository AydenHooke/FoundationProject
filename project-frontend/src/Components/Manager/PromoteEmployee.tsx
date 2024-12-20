import React, { useContext, useEffect } from 'react'
import { userContext } from '../../App';
import { useNavigate } from 'react-router-dom';

function PromoteEmployee() {
  const currentEmployee = useContext(userContext)
    const navigateMe = useNavigate();

    useEffect(()=>
        {
            if(currentEmployee.powerLevel < 0)
                navigateMe('/', {})
      }, [])
  return (
    <div>promoteEmployee</div>
  )
}

export default PromoteEmployee