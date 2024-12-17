import React, { useEffect, useState } from 'react'

function HooksDemo() {
   /*  let [visibility, setVisibility] = useState(false);
    let [count, setCount] = useState(0); */
   // let isVisible = true;

   let [state, setState] = useState({
        visibility: false,
        count:0,
        ticket: {} as any
    })

   /*  useEffect(() => {
        console.log("WHY YOU ENTER MY DOMAIN");
    }, [])  */

   /*  useEffect(()=>{
        async function getTickets(){
            let response = await fetch("http://localhost:8080/tickets?id=0");
            let data = await response.json();
            console.log(data);
            setState({
                ...state,
                ticket: data
            })
            
        }
        getTickets();
    }, []) */

  

    function toggleTextVisibility(){
        //isVisible = !isVisible;
       /*  setVisibility(!visibility); */
        //console.log(isVisible);
        setState({
            ...state,
            visibility: !state.visibility,
            count: state.count
        })
        
    }
  return (
    <div>
        <button onClick={toggleTextVisibility}>Toggle</button>

        {
            state.visibility ? <h2> Am I visible?</h2> : <></>
        }

        <button onClick={() => setState({
            ...state,
            visibility: state.visibility,
            count: state.count + 1
        })}>Increment</button>
        <button onClick={() => setState({
           /*  visibility: !state.visibility,
            count: state.count - 1 */
            ...state,
            visibility: !state.visibility
            })}>Decrement</button>

        <h2>Counter: {state.count}</h2>

        {/* {
            state.ticket.reimbursementDescription ? <h1>Description: { state.ticket[0].reimbursementDescription}</h1> : <h1>WHAT HAPPENING</h1>
        } */}

    </div>
  )
}

export default HooksDemo