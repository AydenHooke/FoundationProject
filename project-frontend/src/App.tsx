import React from 'react';
import logo from './logo.svg';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import NavBar from './Components/NavBar/NavBar';
import Login from './Components/AllVisitors/Login';
import SubmitTicket from './Components/Employee/SubmitTicket';
import ViewMyTickets from './Components/Employee/ViewMyTickets';
import ProcessTickets from './Components/Manager/ProcessTickets';
import PromoteEmployee from './Components/Manager/PromoteEmployee';


function App() {
  return (
    <div className="App">
      <NavBar/>
      <Routes>
        <Route path="/" element={<Login/>}></Route>
            <Route path="/submitTicket/:employeeId" element={<SubmitTicket/>}></Route>
            <Route path="/viewMyTickets/:employeeId" element={<ViewMyTickets/>}></Route>
            
            <Route path="/processTickets/:employeeId" element={<ProcessTickets/>}></Route>
            <Route path="/promoteEmployee/:employeeId" element={<PromoteEmployee/>}></Route>
      </Routes>
    </div>
  );
}

export default App;
