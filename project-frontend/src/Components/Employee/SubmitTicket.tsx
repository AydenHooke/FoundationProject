import React from 'react'
import { useParams } from 'react-router-dom'

function SubmitTicket() {
    const { employeeId } = useParams();
    

  return (
    <>
      <h2 style = {{color: "blue"}}>LOGIN PAGE</h2><br></br>
        <form>
          <label>Username</label><br></br>
          <input type="text" id="username" name="username" required/><br></br>

          <h1></h1> {/* This is just a space --> */}

          <label>Password</label><br></br>
          <input type="password" id="password" name="password" required/><br></br>

          <h1></h1> {/* This is just a space --> */}

          <label>Re-type Password</label><br></br>
          <input type="password" id="rePassword" name="rePassword" required/><br></br>

          <h1></h1> {/* This is just a space --> */}
          
          <input type="submit" value="Login"/>

          <h1></h1> {/* This is just a space --> */}
          
          <br></br><br></br><br></br>

          <input type="submit" value="Create Account"/>
        </form> 
      <div></div> 
    </>
  )
}

export default SubmitTicket