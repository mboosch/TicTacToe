import {useEffect, useState} from "react";
import axios from "axios";

export default function useTicTacToe() {

    const [position, setPosition] = useState<string[]>(Array(9).fill(''))

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
            .catch((error) => {
                console.error(error)})
    }

    return {position, postMove}
}

