import { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useReservationViewModel } from "../viewmodel/useReservationViewModel";

export default function ReservationPage() {
    const vm = useReservationViewModel();
    const { id: movieScreeningId } = useParams();
    const navigate = useNavigate();

    const [selectedSeat, setSelectedSeat] = useState(null);

    const groupedSeats = vm.reservationInfo?.seats.reduce((acc, seat) => {
        if (!acc[seat.row]) {
            acc[seat.row] = [];
        }
        acc[seat.row].push(seat);
        return acc;
    }, {});

    const handleSeatClick = (seat) => {
        if (seat.reserved) return;

        if (selectedSeat?.id === seat.id) {
            setSelectedSeat(null);
        } else {
            setSelectedSeat(seat);
        }
    };

    const handlePayment = () => {
        navigate("/payment", {
            state: {
                seat: selectedSeat,
                movieScreeningId
            }
        });
    };

    return (
        <div>
            <div>
                Data: {vm.reservationInfo?.date}<br />
                Tytuł filmu: {vm.reservationInfo?.movieTitle}<br />
                Czas trwania filmu: {vm.reservationInfo?.duration} minut<br />
                Godzina rozpoczęcia: {vm.reservationInfo?.startTime}<br />
                Numer sali: {vm.reservationInfo?.roomNumber}<br />
                Cena: {vm.reservationInfo?.price} zł
            </div>

            <hr />

            {Object.entries(groupedSeats || {}).map(([row, seats]) => (
                <div
                    key={row}
                    style={{
                        display: "flex",
                        alignItems: "center",
                        gap: "4px",
                        marginBottom: "6px"
                    }}
                >
                    <strong
                        style={{
                            width: "30px",
                            display: "inline-block"
                        }}
                    >
                        {row}
                    </strong>

                    {seats
                        .sort((a, b) => a.number - b.number)
                        .map((seat) => (
                            <button
                                key={seat.id}
                                disabled={seat.reserved}
                                onClick={() => handleSeatClick(seat)}
                                style={{
                                    width: "40px",
                                    height: "40px",
                                    margin: "4px",
                                    backgroundColor: seat.reserved
                                        ? "#ccc"
                                        : selectedSeat?.id === seat.id
                                            ? "#2196f3"
                                            : "#4caf50",
                                    color: "white",
                                    border: selectedSeat?.id === seat.id
                                        ? "3px solid #0d47a1"
                                        : "1px solid #999",
                                    cursor: seat.reserved ? "not-allowed" : "pointer"
                                }}
                            >
                                {seat.number}
                            </button>
                        ))}
                </div>
            ))}

            {selectedSeat && (
                <div style={{ marginTop: "20px" }}>
                    <p>
                        Wybrane miejsce: rząd {selectedSeat.row}, miejsce {selectedSeat.number}
                    </p>

                    <button
                        onClick={handlePayment}
                    >
                        Przejdź do płatności
                    </button>
                </div>
            )}
        </div>
    );
}