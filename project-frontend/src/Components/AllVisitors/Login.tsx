import React, { createContext, useContext, useState } from 'react'
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { userContext } from '../../App';

function Login() {

  const id = useContext(userContext);

  const navigateMe = useNavigate();

  async function logMeIn() {
      const userInfo = await fetch("http://localhost:8080/accountService", {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({username: id.username, password: id.password})
      }

      

      )
      if(userInfo.status == 201)
        window.alert("Your account as been successfully created!")
      else if (userInfo.status == 200)
        navigateMe(`/EmployeeWelcome`, {});
      else
        window.alert("This username already exists - try a new one!")

    
  }

  const checkLogin = (event : any) => {
    event.preventDefault()

    const data = new FormData(event.currentTarget);
    let checkUsername = data.get("username");
    let checkPassword = data.get("password");
    let checkRePassword = data.get("rePassword");
    if(checkPassword == checkRePassword){
        id.username = checkUsername as string;
        id.password = checkPassword as string;
        logMeIn();
    }else{
        window.alert("Your passwords do not match!");
        return;
      }
    

  }

  return (
    <>
<ul className="nav nav-pills">
  <li className="nav-item">
    <Link className="nav-link active" aria-current="page" to="/">Login</Link>
  </li>
</ul>

      <h2 style = {{color: "blue"}}>LOGIN PAGE</h2><br></br>
        <form onSubmit={checkLogin}>
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

export default Login