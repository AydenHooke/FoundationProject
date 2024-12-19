import React from 'react'
import { useParams } from 'react-router-dom'

function SubmitTicket() {
    const { employeeId } = useParams();

  return (
    <div>submitTicket under user {employeeId} </div>
  )
}

export default SubmitTicket