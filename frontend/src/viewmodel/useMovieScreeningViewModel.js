import {useEffect, useState} from "react";
import { getMovieScreenings} from "../model/movieScreeningApi";

export function useMovieScreeningViewModel(){
    const [movieScreenings, setMovieScreenings] = useState([]);

    useEffect( () => {
        const fetchData = async () => {
            const data = await getMovieScreenings();
            setMovieScreenings(data);
        }
        fetchData();
    }, []);

    return {
        movieScreenings
    };
}