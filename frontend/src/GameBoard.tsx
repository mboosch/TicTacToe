import './TicTacToe.css'

import useTicTacToe from "./hooks/useTicTacToe";
import Row from "./Row";

export default function GameBoard() {
    const {position, postMove, } = useTicTacToe()

    return (
        <div className='container'>
            <h1 className='title'>Tic Tac Toe</h1>
            <div className='board'>
                {[1, 2, 3].map(index => (
                    <Row key={index} position={position} rowNumber={index} postMove={postMove}/>
                ))
                }
            </div>
        </div>
    );
}