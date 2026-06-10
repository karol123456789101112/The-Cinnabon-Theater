export const handlePayment = async ({ seatId, movieScreeningId }) => {
    const response = await fetch("http://localhost:8081/tickets/addTicket", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            seatId,
            movieScreeningId
        })
    });

    if (!response.ok) {
        throw new Error("Payment failed");
    }

    return response.json();
};