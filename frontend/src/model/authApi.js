export const loginRequest = async (email, password) => {
    const response = await fetch("http://localhost:8081/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, password })
    });

    const data = await response.json();

    if (!response.ok) {
        throw data;
    }

    return data;
};