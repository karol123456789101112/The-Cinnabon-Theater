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

export const deleteMovie = async (id) => {
    const token = localStorage.getItem('token');
    const response = await fetch(`http://localhost:8081/movies/delete/${id}`, {
        method: 'DELETE',
        headers: { 'Authorization': `Bearer ${token}`},
    });

    if(!response.ok){
        throw new Error("Failed to delete movie");
    }
}

export const deleteMovieScreening = async (id) => {
    const token = localStorage.getItem('token');
    const response = await fetch(`http://localhost:8081/movieScreenings/delete/${id}`, {
        method: "DELETE",
        headers: { 'Authorization': `Bearer ${token}`},
    });

    if(!response.ok) {
        throw new Error("Failed to delete movie screening");
    }
}

export const getAllGenres = async () => {
    const response = await fetch("http://localhost:8081/genres");

    const allGenres = response.json();

    if(!response) {
        throw allGenres;
    } else{
        return allGenres;
    }
}

export const addMovie = async (form) => {
    const token = localStorage.getItem('token');
    const response = await fetch("http://localhost:8081/movies/addMovie", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({
            name: form.name,
            duration: form.duration,
            genreIds: form.genreIds
        })
    });

    if (!response.ok) {
       throw new Error("Failed to add movie");
    }
}