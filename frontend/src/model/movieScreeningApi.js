export const getMovieScreenings = async () => {
    const response = await fetch("http://localhost:8081/movieScreenings");

    const data = await response.json();

    if (!response.ok) {
        throw data;
    }

    return data;
};