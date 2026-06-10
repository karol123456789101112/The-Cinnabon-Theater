import {deleteUser, getAllUsers, toggleAdmin} from "../model/adminApi";
import {useEffect, useState} from "react";

export function useAdminViewModel(){

    const [allUsers, setAllUsers] = useState([])
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

    useEffect(() => {
        fetchUsers();
    }, []);

    return {
        allUsers,
        error,
        loading,
        fetchUsers,
        handleDeleteUser,
        handleToggleRole
    }
}