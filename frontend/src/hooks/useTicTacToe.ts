import {useEffect, useState} from "react";
import axios from "axios";

export default function useTicTacToe() {

    const [position, setPosition] = useState<string[]>(Array(9).fill(''))
    const [gameStatus, setGameStatus] = useState<string>('ongoing')

    const getPosition = () => {
        axios.get('api/tictactoe/')
            .then(response => {
                setPosition(response.data)})
            .catch(error => console.error(error))
    }

    useEffect(
        () => getPosition(), []
    )

    const postMove = (move: number) => {
        return axios.post('api/tictactoe/' + move)
            .then(getPosition)
            .then(checkGameStatus)
            .catch((error) => {
                console.error(error)})
    }

    const checkGameStatus = () => {
        return axios.get('api/tictactoe/status')
            .then(response => {
                setGameStatus(response.data)})
            .catch(error => console.error(error))
    }

    const resetBoard = () => {
        return axios.get('api/tictactoe/reset')
            .then(getPosition)
            .then(checkGameStatus)
            .catch(error => console.error(error))
    }


    return {position, postMove, gameStatus, resetBoard}
}

