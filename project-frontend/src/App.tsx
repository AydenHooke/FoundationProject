import React, { createContext, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import NavBar from './Components/NavBar/NavBar';
import Login from './Components/AllVisitors/Login';
import SubmitTicket from './Components/Employee/SubmitTicket';
import ViewMyTickets from './Components/Employee/ViewMyTickets';
import ProcessTickets from './Components/Manager/ProcessTickets';
import PromoteEmployee from './Components/Manager/PromoteEmployee';
import EmployeeWelcome from './Components/Employee/EmployeeWelcome';
import ManagerWelcome from './Components/Manager/ManagerWelcome';
import ViewAllTickets from './Components/Manager/ViewAllTickets';


const currentEmployee = {
  username: "",
  password: "",
  employeeId: -1,
  powerLevel: -1
}

export const userContext = createContext(currentEmployee);

function App() {
  return (
    <div className="App">
      {/* <NavBar/> */}
      <userContext.Provider value={currentEmployee}>
        <Routes>
          <Route path="*" element={<Login/>}/>
          <Route path="home/*" element={<EmployeeWelcome/>}>
            <Route path="submitTicket" element={<SubmitTicket/>}/>
            <Route path="viewMyTickets" element={<ViewMyTickets/>}/>
          </Route>
          <Route path="lounge/*" element={<ManagerWelcome/>}>
            <Route path="processTickets" element={<ProcessTickets/>}/>
            <Route path="viewAllTickets" element={<ViewAllTickets/>}/>
            <Route path="promoteEmployee" element={<PromoteEmployee/>}/>
          </Route>
        </Routes>
      </userContext.Provider>
    </div>
  );
}

export default App;
