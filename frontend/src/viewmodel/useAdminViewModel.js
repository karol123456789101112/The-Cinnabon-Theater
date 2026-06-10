import {deleteUser, getAllMovies, getAllMovieScreenings, getAllUsers, toggleAdmin} from "../model/adminApi";
import {useEffect, useState} from "react";

export function useAdminViewModel(){

    const [allUsers, setAllUsers] = useState([])
    const [allMovieScreenings, setAllMovieScreenings] = useState([])
    const [allMovies, setAllMovies] = useState([]);
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
        }
    }

    const fetchMovies = async () => {
        try{
            const movies = await getAllMovies();
            setAllMovies(movies);
        } catch (err) {
            console.error("Error while fetching movies " + err);
        }
    }

    useEffect(() => {
        fetchUsers();
        fetchMovieScreenings();
        fetchMovies();
    }, []);

    return {
        allUsers,
        allMovieScreenings,
        allMovies,
        error,
        loading,
        fetchUsers,
        handleDeleteUser,
        handleToggleRole,
        fetchMovieScreenings,
        fetchMovies
    }
}