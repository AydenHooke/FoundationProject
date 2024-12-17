import React from 'react'

function EventsDemo() {

    function clickEventTriggered(){
        console.log("Click button triggered");
    }

    function inputChanged(event: any){
        //console.log("input changed");
        console.log(event.target.value);
    }
  return (
    <>
        <button onClick={clickEventTriggered}>Click Event</button>
        <button onMouseOver={() => console.log("hover event triggered")}> TOUCH ME</button>
        <input type="text" onChange={inputChanged}></input>
    </>
  )
}

export default EventsDemo