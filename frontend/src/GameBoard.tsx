import './TicTacToe.css'

import useTicTacToe from "./hooks/useTicTacToe";
import Row from "./Row";

export default function GameBoard() {
    const {position, postMove, gameStatus} = useTicTacToe()

    return (
        <div className='container'>
            <h1 className='title'>Tic Tac Toe</h1>
            <div className='board'>
                {[1, 2, 3].map(index => (
                    <Row key={index} position={position} rowNumber={index} postMove={postMove} gameStatus={gameStatus}/>
                ))
                }
            </div>
            {gameStatus === "playersVictory" ? <h2 className={"gameEnd"}>Du hast gewonnen!</h2> :
                gameStatus === "computersVictory" ? <h2 className={"gameEnd"}>Du hast verloren.</h2> :
                    gameStatus === "draw" ? <h2 className={"gameEnd"}>Das Spiel ist unentschieden.</h2> : null}
        </div>
    );
}