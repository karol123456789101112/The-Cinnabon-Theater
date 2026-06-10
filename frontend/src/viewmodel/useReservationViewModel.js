import {useState, useEffect} from "react";
import {getReservationInfo} from "../model/reservationApi";
import {useParams} from "react-router-dom";

export function useReservationViewModel(){

    const [reservationInfo, setReservationInfo] = useState(null);
    const [error, setError] = useState(null)
    const { id } = useParams()

    useEffect( () => {
        const fetchData = async () => {
            try {
                const data = await getReservationInfo(id);
                setReservationInfo(data);
            } catch (err) {
                console.error(err);
                setError(err);
            }
        }

        fetchData();
    }, [id])

    return {
        reservationInfo,
        error
    };
}