import {useState, useEffect} from "react";
import {getReservationInfo} from "../model/reservationApi";
import {useParams} from "react-router-dom";

export function useReservationViewModel(){

    const [reservationInfo, setReservationInfo] = useState(null);
    const { id } = useParams()

    useEffect( () => {
        const fetchData = async () => {
            const data = await getReservationInfo(id);

            setReservationInfo(data);
        }

        fetchData();
    }, [id])

    return {
      reservationInfo
    };
}