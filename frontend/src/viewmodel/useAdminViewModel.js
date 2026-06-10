import {getAllUsers} from "../model/adminApi";
import {useEffect, useState} from "react";

export function useAdminViewModel(){

    const [allUsers, setAllUsers] = useState([])
    const [error, setError] = useState(null)

    useEffect(() => {
        const fetchData = async () => {
            try{
                const data = await getAllUsers();
                setAllUsers(data);
            } catch (err){
                console.error(err);
                setError(err);
            }
        }
        fetchData()
    },[])


    return {
        allUsers,
        error
    }
}