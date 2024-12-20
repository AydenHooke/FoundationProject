import React, { useContext, useEffect } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { userContext } from '../../App';

function SubmitTicket() {
    const currentEmployee = useContext(userContext)
        const navigateMe = useNavigate();
    
        useEffect(()=>
            {
                if(currentEmployee.powerLevel < 0)
                    navigateMe('/', {})
          }, [])
  
    const checkTicketInfo = (event : any) => {
      event.preventDefault();
      const data = new FormData(event.currentTarget);
      let checkAmount = data.get("amount");
      let checkDescription = data.get("description");

      async function submitATicket() {
        const userTicket = await fetch(`http://localhost:8080/ticketCreation?employeeId=${currentEmployee.employeeId}`, {
          method: 'POST',
          headers: {'Content-Type': 'application/json'},
          body: JSON.stringify({reimbursementAmount: checkAmount, reimbursementDescription: checkDescription})
        })

        if(userTicket.status == 201){
          (document.getElementById('form') as HTMLFormElement).reset();
          window.alert("Your request has been successfully submitted!");
        }
        else
          window.alert("There was an error submitting your request");
      }
      

    submitATicket();
    }

  return (
    <>
    <h1></h1>
      <h1 style = {{color: "yellow"}}>SUBMIT A TICKET</h1><br></br>
        <form onSubmit={checkTicketInfo} id = "form">
          <label>How much do you need to be reimbursed?</label><br></br>
          <h1></h1>
          <div style = {{width:"27px", display:'inline-block', verticalAlign:'baseline'}}>$</div>
          <input type="text" id="amount" name="amount" required style = {{marginRight:'27px'}}/><br></br>

          <h1></h1>{/* This is just a space --> */}
          <br></br>
          <h1></h1>{/* This is just a space --> */}

          <label>Please elaborate on your reimbursement:</label><br></br>
          <h1></h1>
          <textarea id="description" name="description" style = {{width: "314px", height: "159px",}} required/><br></br>

          <h1></h1> {/* This is just a space --> */}

          
          

          <input type="submit" value="Apply for Reimbursement"/>
        </form> 
      <div></div> 
    </>
  )
}

export default SubmitTicket