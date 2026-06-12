import {
    addMovie, addMovieScreening,
    deleteMovie, deleteMovieScreening,
    deleteUser, getAllGenres,
    getAllMovies,
    getAllMovieScreenings, getAllScreeningRooms,
    getAllUsers,
    toggleAdmin, updateMovie, updateMovieScreening
} from "../model/adminApi";
import {useEffect, useState} from "react";

export function useAdminViewModel(){

    const [allUsers, setAllUsers] = useState([])
    const [allMovieScreenings, setAllMovieScreenings] = useState([])
    const [allMovies, setAllMovies] = useState([]);
    const [allGenres, setAllGenres] = useState([]);
    const [allScreeningRooms, setAllScreeningRooms] = useState([]);
    const emptyMovieForm = {
        name: "",
        duration: "",
        genreIds: []
    };
    const [addMovieForm, setAddMovieForm] = useState(emptyMovieForm);
    const [editMovieForm, setEditMovieForm] = useState(emptyMovieForm);
    const [editingMovieId, setEditingMovieId] = useState(null);

    const emptyMovieScreeningForm = {
        price: "",
        startTime: "",
        movieId: "",
        screeningRoomId: ""
    };

    const [addMovieScreeningForm, setAddMovieScreeningForm] =
        useState(emptyMovieScreeningForm);

    const [editMovieScreeningForm, setEditMovieScreeningForm] =
        useState(emptyMovieScreeningForm);

    const [editingMovieScreeningId, setEditingMovieScreeningId] =
        useState(null);

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

    function updateAddMovieField(field, value) {
        setAddMovieForm(prev => ({
            ...prev,
            [field]: value
        }));
    }

    function updateEditMovieField(field, value) {
        setEditMovieForm(prev => ({
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

    async function submitMovieForm() {
        try {
            if (editingMovieId) {
                const updated = await updateMovie(editingMovieId, editMovieForm);

                setAllMovies(prev =>
                    prev.map(m =>
                        m.id === updated.id ? updated : m
                    )
                );

                setEditingMovieId(null);
                setEditMovieForm(emptyMovieForm);

            } else {
                const newMovie = await addMovie(addMovieForm);

                setAllMovies(prev => [...prev, newMovie]);

                setAddMovieForm(emptyMovieForm);
            }

        } catch (err) {
            console.error(err);
        }
    }

    const submitAddMovieScreeningForm = async () => {
        try {
            const newMovieScreening = await addMovieScreening(addMovieScreeningForm);
            setAllMovieScreenings(prev => [...prev, newMovieScreening]);
        } catch (err) {
            console.error("Error while adding movie screening " + err)
        } finally {
            setLoading(false)
        }
    }

    const fetchScreeningRooms = async () => {
        try {
            const screeningRooms = await getAllScreeningRooms();
            setAllScreeningRooms(screeningRooms);
        } catch (err) {
            console.error("Error while fetching screening rooms " + err);
        }
    }

    function startEditing(movie) {
        setEditingMovieId(movie.id);

        setEditMovieForm({
            name: movie.name,
            duration: movie.duration,
            genreIds: movie.genres.map(g => g.id)
        });
    }

    function cancelEditing() {
        setEditingMovieId(null);
        setEditMovieForm(emptyMovieForm);
    }

    function updateAddMovieScreeningField(field, value) {
        setAddMovieScreeningForm(prev => ({
            ...prev,
            [field]: value
        }));
    }

    function updateEditMovieScreeningField(field, value) {
        setEditMovieScreeningForm(prev => ({
            ...prev,
            [field]: value
        }));
    }

    function startEditingMovieScreening(screening) {
        console.log(screening);
        setEditingMovieScreeningId(screening.id);

        setEditMovieScreeningForm({
            price: screening.price,
            startTime: screening.startTime,
            movieId: screening.movieId,
            screeningRoomId: screening.screeningRoomId
        });
    }

    function cancelEditingMovieScreening() {
        setEditingMovieScreeningId(null);
        setEditMovieScreeningForm(emptyMovieScreeningForm);
    }

    async function submitMovieScreeningForm() {
        try {
            if (editingMovieScreeningId) {
                const updated = await updateMovieScreening(
                    editingMovieScreeningId,
                    editMovieScreeningForm
                );

                setAllMovieScreenings(prev =>
                    prev.map(ms =>
                        ms.id === updated.id ? updated : ms
                    )
                );

                setEditingMovieScreeningId(null);
                setEditMovieScreeningForm(emptyMovieScreeningForm);

            } else {
                const created = await addMovieScreening(
                    addMovieScreeningForm
                );

                setAllMovieScreenings(prev => [...prev, created]);

                setAddMovieScreeningForm(emptyMovieScreeningForm);
            }
        } catch (err) {
            console.error(err);
        }
    }

    useEffect(() => {
        fetchUsers();
        fetchMovieScreenings();
        fetchMovies();
        fetchGenres();
        fetchScreeningRooms()
    }, []);

    return {
        allUsers,
        allMovieScreenings,
        allMovies,
        addMovieForm,
        editMovieForm,
        addMovieScreeningForm,
        allGenres,
        allScreeningRooms,
        editingMovieId,
        editingMovieScreeningId,
        editMovieScreeningForm,
        error,
        loading,
        fetchUsers,
        handleDeleteUser,
        handleToggleRole,
        fetchMovieScreenings,
        fetchMovies,
        handleDeleteMovie,
        handleDeleteMovieScreening,
        fetchGenres,
        submitMovieForm,
        submitAddMovieScreeningForm,
        startEditing,
        cancelEditing,
        updateEditMovieField,
        updateAddMovieField,
        startEditingMovieScreening,
        updateEditMovieScreeningField,
        submitMovieScreeningForm,
        cancelEditingMovieScreening,
        updateAddMovieScreeningField
    }
}