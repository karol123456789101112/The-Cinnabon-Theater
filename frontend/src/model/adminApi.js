export const getAllUsers = async () => {
    const response = await fetch("http://localhost:8081/api/users/listAllUsers");

    const data = await response.json();

    if (!response.ok){
        throw data;
    }

    return data;
};