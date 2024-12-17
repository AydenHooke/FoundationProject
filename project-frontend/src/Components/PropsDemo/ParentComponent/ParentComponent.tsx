import React from 'react'
import ChildComponent from '../ChildComponent/ChildComponent'

function ParentComponent() {
  return (
    <div>
        <ChildComponent backgroundColor="red" isBold={true}/>
        <ChildComponent backgroundColor="blue" isBold={false}/>
        <ChildComponent backgroundColor="yellow" isBold={true}/>
    </div>
  )
}

export default ParentComponent