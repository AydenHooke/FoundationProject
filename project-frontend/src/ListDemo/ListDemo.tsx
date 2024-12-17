import React from 'react'

const DUMMY_ARRAY = [
    {
        firstName: "jay jay",
        lastName: "stupendous"
    },
    {
        firstName: "fraggart",
        lastName: "malard"
    }
]

function ListDemo() {
  return (
    <>
        <h1>{DUMMY_ARRAY[0].firstName}</h1>
        {
            DUMMY_ARRAY.map((obj, index) => {
                return (
                    <div key={index}>
                        <h3>{obj.firstName}</h3>
                        <h3>{obj.lastName}</h3>
                        <button 
                        onDoubleClick = {()=>console.log("OHHHHHH THAT FEELS SO GOOD")} 
                        onClick = {() => console.log("faster...faster...")}
                        onMouseOver = {() => console.log("pleeeeeease touch me")}>Click me dammit</button>
                    </div>                    
                )
            })
        }
    </>
  )
}

export default ListDemo