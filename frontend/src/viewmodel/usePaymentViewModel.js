import { useState, useEffect } from "react";
import { getReservationInfo } from "../model/reservationApi";

export function usePaymentViewModel(movieScreeningId) {

    const [reservationInfo, setReservationInfo] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            const data = await getReservationInfo(movieScreeningId);
            setReservationInfo(data);
        };

        fetchData();
    }, [movieScreeningId]);

    return {
        reservationInfo
    };
}