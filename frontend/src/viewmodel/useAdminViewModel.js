import {
    addMovie,
    deleteMovie, deleteMovieScreening,
    deleteUser, getAllGenres,
    getAllMovies,
    getAllMovieScreenings,
    getAllUsers,
    toggleAdmin
} from "../model/adminApi";
import {useEffect, useState} from "react";

export function useAdminViewModel(){

    const [allUsers, setAllUsers] = useState([])
    const [allMovieScreenings, setAllMovieScreenings] = useState([])
    const [allMovies, setAllMovies] = useState([]);
    const [allGenres, setAllGenres] = useState([]);
    const [addMovieForm, setAddMovieForm] = useState({
        name: "",
        duration: "",
        genreIds: []
    });
    const [error, setError] = useState(null)
    const [loading, setLoading] = useState(true);

    const fetchUsers = async () => {
        try{
            const data = await getAllUsers();
            setAllUsers(data);
        } catch (err){
            console.error(err);
            setError(err);
        } finally {
            setLoading(false);
        }
    };

    const handleDeleteUser = async (id) => {
        try {
            await deleteUser(id);
            setAllUsers(prev => prev.filter(u => u.id !== id));
        } catch (err) {
            console.error(err);
            setError(err);
        }
    };

    const handleToggleRole = async (id) => {
        try{
            const updatedUser = await toggleAdmin(id);
            setAllUsers(prev => prev.map(user => user.id === id ? updatedUser : user));
        } catch (err) {
            console.error("Error while changing user role " + err);
        }
    };

    const fetchMovieScreenings = async () => {
        try{
            const movieScreenings = await getAllMovieScreenings();
            setAllMovieScreenings(movieScreenings);
        } catch (err) {
            console.error("Error while fetching movie screenings " + err);
        } finally {
            setLoading(false);
        }
    }

    const fetchMovies = async () => {
        try{
            const movies = await getAllMovies();
            setAllMovies(movies);
        } catch (err) {
            console.error("Error while fetching movies " + err);
        } finally {
            setLoading(false);
        }
    }

    const handleDeleteMovie = async (id) => {
        try {
            await deleteMovie(id);
            setAllMovies(prev => prev.filter(m => m.id !== id));
        } catch (err) {
            console.error("Error while deleting movie " + err);
        }
    }

    const handleDeleteMovieScreening = async (id) => {
        try {
            await deleteMovieScreening(id);
            setAllMovieScreenings(prev => prev.filter(ms => ms.id !== id));
        } catch (err) {
            console.error("Error while deleting movie " + err);
        }
    }

    function updateField(field, value) {
        setAddMovieForm( prev => ({
            ...prev,
            [field]: value
        }));
    }

    const fetchGenres = async () => {
        try {
            const genres = await getAllGenres();
            setAllGenres(genres);
        } catch (err) {
            console.error("Failed to fetch genres " + err);
        } finally {
            setLoading(false);
        }
    }

    const submitAddMovieForm = async () => {
        try {
            await addMovie(addMovieForm)
        } catch (err) {
            console.error("Error while adding movie " + err);
        } finally {
            setLoading(false);
        }
    }


    useEffect(() => {
        fetchUsers();
        fetchMovieScreenings();
        fetchMovies();
        fetchGenres();
    }, []);

    return {
        allUsers,
        allMovieScreenings,
        allMovies,
        addMovieForm,
        allGenres,
        error,
        loading,
        fetchUsers,
        handleDeleteUser,
        handleToggleRole,
        fetchMovieScreenings,
        fetchMovies,
        handleDeleteMovie,
        handleDeleteMovieScreening,
        updateField,
        fetchGenres,
        submitAddMovieForm
    }
}