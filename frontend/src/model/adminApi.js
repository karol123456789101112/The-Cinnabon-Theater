export const getAllUsers = async () => {
    const response = await fetch("http://localhost:8081/api/users/listAllUsers");

    const data = await response.json();

    if (!response.ok){
        throw data;
    }

    return data;
};

export const deleteUser = async (id) => {
    const token = localStorage.getItem('token');
    const response = await fetch(`http://localhost:8081/api/users/deleteUser/${id}`, {
        method: 'DELETE',
        headers: { 'Authorization': `Bearer ${token}`},
    });

    if (!response.ok){
        throw new Error("Failed to delete user");
    }
};

export const toggleAdmin = async (id) => {
    const token = localStorage.getItem('token');
    const response = await fetch(`http://localhost:8081/api/users/toggleAdmin/${id}`,{
        method: 'PUT',
        headers: { 'Authorization': `Bearer ${token}`},
    });

    if (!response.ok){
        throw new Error("Failed to toggle user role")
    }else{
        return await response.json();
    }
};

export const getAllMovieScreenings = async () => {
    const response = await fetch("http://localhost:8081/movieScreenings/all");
    const data = await response.json();

    if(!response.ok){
        throw data;
    }

    return data;
}

export const getAllMovies = async () => {
    const response = await fetch("http://localhost:8081/movies");
    const movies = await response.json();

    if(!response.ok){
        throw movies
    }

    return movies;
}