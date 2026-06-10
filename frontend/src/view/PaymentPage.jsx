import {useLocation} from "react-router-dom";
import {useNavigate} from "react-router-dom";
import {usePaymentViewModel} from "../viewmodel/usePaymentViewModel";
import {useScreeningReservationViewModel} from "../viewmodel/useScreeningReservationViewModel";

export default function PaymentPage() {
    const location = useLocation();
    const navigate = useNavigate();

    const { seat, movieScreeningId } = location.state || {};

    const vm = usePaymentViewModel();
    const resVM = useScreeningReservationViewModel(movieScreeningId)

    if (!seat || !movieScreeningId) {
        return (
            <div>
                <h2>Błąd</h2>
                <button onClick={() => navigate(-1)}>Wróć</button>
            </div>
        );
    }

    const onPay = async () => {
        const ok = await vm.pay({
            seatId: seat.id,
            movieScreeningId
        });

        if (ok) {
            setTimeout(() => navigate("/"), 2000);
        }
    };

    if (vm.success) {
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

            Data: {resVM.reservationInfo?.date}<br />
            Tytuł filmu: {resVM.reservationInfo?.movieTitle}<br />
            Czas trwania filmu: {resVM.reservationInfo?.duration} minut<br />
            Godzina rozpoczęcia: {resVM.reservationInfo?.startTime}<br />
            Numer sali: {resVM.reservationInfo?.roomNumber}<br />
            Miejsce: {seat.number} {seat.row} <br />
            Cena: {resVM.reservationInfo?.price} zł<br /><br />

            <button onClick={onPay} disabled={vm.loading}>
                {vm.loading ? "Przetwarzanie..." : "Zapłać"}
            </button>
        </div>
    );
}