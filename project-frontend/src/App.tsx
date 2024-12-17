import React from 'react';
import logo from './logo.svg';
import './App.css';
import FirstComponent from './Components/FirstComponent/FirstComponent';
import EventsDemo from './Events/EventsDemo';
import ListDemo from './ListDemo/ListDemo';
import ParentComponent from './PropsDemo/ParentComponent/ParentComponent';

function App() {
  return (
    <div className="App">
      <FirstComponent/>
      <EventsDemo/>
      <ListDemo/>
      <ParentComponent/>
    </div>
  );
}

export default App;
