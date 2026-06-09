export const getReservationInfo = async (id) => {
    const response = await fetch(`http://localhost:8081/reservations/${id}`);

    const data = await response.json();

    if(!response.ok){
        throw data;
    }

    return data;
}