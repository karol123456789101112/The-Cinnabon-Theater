import { useState, useEffect } from "react";
import { getReservationInfo } from "../model/reservationApi";

export function useScreeningReservationViewModel(movieScreeningId) {

    const [reservationInfo, setReservationInfo] = useState(null);
    const [error, setError] = useState(null)

    useEffect(() => {
        const fetchData = async () => {
            try {
                const data = await getReservationInfo(movieScreeningId);
                setReservationInfo(data);
            } catch(err) {
                console.error(err);
                setError(err);
            }

        };

        fetchData();
    }, [movieScreeningId]);

    return {
        reservationInfo,
        error
    };
}