import { useLocation, useNavigate } from "react-router-dom";
import { useState } from "react";
import {usePaymentViewModel} from "../viewmodel/usePaymentViewModel";

export default function PaymentPage() {
    const location = useLocation();
    const navigate = useNavigate();

    const { seat, movieScreeningId } = location.state || {};

    const [loading, setLoading] = useState(false);
    const [success, setSuccess] = useState(false);

    const vm = usePaymentViewModel(movieScreeningId);

    if (!seat || !movieScreeningId) {
        return (
            <div>
                <h2>Błąd</h2>
                <p>Brak danych do płatności</p>
                <button onClick={() => navigate(-1)}>Wróć</button>
            </div>
        );
    }

    const handlePayment = async () => {
        setLoading(true);

        try {
            const response = await fetch("http://localhost:8081/tickets/addTicket", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    seatId: seat.id,
                    movieScreeningId,
                })
            });

            if (!response.ok) {
                throw new Error("Payment failed " + seat.id + "  " + movieScreeningId);
            }

            setSuccess(true);

            setTimeout(() => {
                navigate("/");
            }, 2000);

        } catch (err) {
            console.error(err);
            alert("Błąd płatności");
        } finally {
            setLoading(false);
        }
    };

    if (success) {
        return (
            <div>
                <h2>Płatność zakończona</h2>
                <p>Bilet został utworzony</p>
            </div>
        );
    }

    return (
        <div>
            <h2>Płatność</h2>

            Data: {vm.reservationInfo?.date}<br />
            Tytuł filmu: {vm.reservationInfo?.movieTitle}<br />
            Czas trwania: {vm.reservationInfo?.duration} minut<br />
            Godzina rozpoczęcia: {vm.reservationInfo?.startTime}<br />
            Numer sali: {vm.reservationInfo?.roomNumber}<br />
            Miejsce: {seat.number} {seat.row}<br />
            Cena: {vm.reservationInfo?.price} zł<br /><br />

            <button onClick={handlePayment} disabled={loading}>
                {loading ? "Przetwarzanie..." : "Zapłać"}
            </button>
        </div>
    );
}