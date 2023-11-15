import cross_icon from "./assets/cross.png";
import circle_icon from "./assets/circle.png";
import React from "react";

export type RowProps = {
    position: string[]
    rowNumber: number
    postMove: (move:number) => void
}

export default function Row(props: RowProps)  {
    let boxes: number[];
    if (props.rowNumber === 1) {
        boxes = [0,1,2]
    } else { if (props.rowNumber === 2) {
        boxes = [3,4,5]
    } else {
        boxes = [6,7,8]
    }}

    const toggle = (e: React.MouseEvent<HTMLDivElement>, index: number) => {
        if ((props.position[index] === ' ' || props.position[index] === undefined)) {
            props.postMove(index)
        }
    }

    return (
        <div className='row'>
            {boxes.map((index) => (
                <div key={index} className={'boxes'} onClick={(e) => {
                    toggle(e, index)
                }}>
                    {props.position[index] === 'X' && <img src={cross_icon} alt='X'/>}
                    {props.position[index] === 'O' && <img src={circle_icon} alt={'O'}/>}
                </div>
            ))}
        </div>
    )
}