export const getMovieScreenings = async (email, password) => {
    const response = await fetch("http://localhost:8081/movieScreenings", {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        },
    });

    const data = await response.json();

    if (!response.ok) {
        throw data;
    }

    return data;
};