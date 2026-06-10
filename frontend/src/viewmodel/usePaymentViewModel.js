import { useState } from "react";
import { handlePayment } from "../model/paymentApi";

export function usePaymentViewModel() {
    const [loading, setLoading] = useState(false);
    const [success, setSuccess] = useState(false);

    const pay = async ({ seatId, movieScreeningId }) => {
        setLoading(true);

        try {
            await handlePayment({ seatId, movieScreeningId });
            setSuccess(true);
            return true;
        } catch (e) {
            console.error(e);
            return false;
        } finally {
            setLoading(false);
        }
    };

    return {
        loading,
        success,
        pay
    };
}