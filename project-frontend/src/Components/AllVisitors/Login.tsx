import React, { createContext, useContext, useEffect, useState } from 'react'
import { Link, Route, Routes, useLocation, useNavigate } from 'react-router-dom';
import { userContext } from '../../App';
import SubmitTicket from '../Employee/SubmitTicket';

function Login() {

  const currentEmployee = useContext(userContext);

  useEffect(()=>
    {
      currentEmployee.username = "";
      currentEmployee.employeeId = -1; // removes values on logout i.e. first page reload
      currentEmployee.powerLevel = -1;
  }, [])

  const navigateMe = useNavigate();

  

  const checkLogin = (event : any) => {
    event.preventDefault()

    const data = new FormData(event.currentTarget);
    let checkUsername = data.get("username");
    let checkPassword = data.get("password");
    let checkRePassword = data.get("rePassword");

    if(checkPassword == checkRePassword)
      currentEmployee.username = checkUsername as string;
    else{
      window.alert("Your passwords do not match!");
      return;
    }

      async function logMeIn() {
        const userInfo = await fetch("http://localhost:8080/accountService", {
          method: 'POST',
          headers: {'Content-Type': 'application/json'},
          body: JSON.stringify({username: currentEmployee.username, password: checkPassword as string})
        })   

        try{
          let loggedUser = await userInfo.json();
          currentEmployee.powerLevel = loggedUser.accessLevel;
          currentEmployee.employeeId = loggedUser.employeeId;
        }catch(e){}

        if(userInfo.status == 201){
          (document.getElementById('form') as HTMLFormElement).reset();
          window.alert("Your account has been successfully created!")
        }
        else if (userInfo.status == 200 && currentEmployee.powerLevel == 0)
          navigateMe(`/home`, {});
        else if (userInfo.status == 200 && currentEmployee.powerLevel > 0)
          navigateMe(`/lounge`, {});
        else if (userInfo.status != 409)
          window.alert("Someone with this username already exists");
        else
          window.alert("A problem has occured - try again later")
      }
    
    
    logMeIn();
  }

  return (
    <>
<ul className="nav nav-pills">
  <li className="nav-item">
    <label className="nav-link active" >Login</label>
  </li>
</ul>

      <h2 style = {{color: "blue"}}>LOGIN PAGE</h2><br></br>
        <form onSubmit={checkLogin} id = "form">
          <label>Username</label><br></br>
          <input id="username" name="username" required/><br></br>

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