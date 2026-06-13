import {useEffect, useState} from "react";
import { getMovieScreenings} from "../model/movieScreeningApi";

export function useMovieScreeningViewModel(){
    const [movieScreenings, setMovieScreenings] = useState([]);
    const [error, setError] = useState(null)
    useEffect( () => {
        const fetchData = async () => {
            try {
                const data = await getMovieScreenings();
                setMovieScreenings(data);
            } catch (err){
                console.error(err);
                setError(err);
            }

        }
        fetchData();
    }, []);

    return {
        movieScreenings,
        error
    };
}