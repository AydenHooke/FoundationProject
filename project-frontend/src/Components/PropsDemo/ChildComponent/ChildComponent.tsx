import React from 'react'

function ChildComponent(props: {backgroundColor: string, isBold: boolean}) {
  return (
    <div style={
        {   backgroundColor: props.backgroundColor,
            fontWeight: props.isBold ? "bolder" : "lighter"
        }
    }onMouseOver = {()=>console.log("dis me")
    }>I'M JUST A BABY</div>
  )
}

export default ChildComponent