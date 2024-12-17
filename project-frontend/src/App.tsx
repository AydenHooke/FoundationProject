import React from 'react';
import logo from './logo.svg';
import './App.css';
import FirstComponent from './Components/FirstComponent/FirstComponent';
import EventsDemo from './Components/Events/EventsDemo';
import ListDemo from './Components/ListDemo/ListDemo';
import ParentComponent from './Components/PropsDemo/ParentComponent/ParentComponent';
import { Route, Routes } from 'react-router-dom';
import NavBar from './Components/NavBar/NavBar';
import HooksDemo from './Components/Hooks/HooksDemo';
import UserManagement from './Components/FormInput/SmartComponent/UserManagement';

function App() {
  return (
    <div className="App">
      {/* <FirstComponent/>
      <EventsDemo/>
      <ListDemo/>
      <ParentComponent/> */}
      <NavBar/>
      <Routes>
        <Route path="/events" element={<EventsDemo/>}></Route>
        <Route path="/hooks" element={<HooksDemo/>}></Route>
        <Route path="/login" element={<UserManagement/>}></Route>
      </Routes>
    </div>
  );
}

export default App;
