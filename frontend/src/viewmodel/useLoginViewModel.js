
import { useState } from "react";
import { loginRequest } from "../view/api/authApi";
import { useAuth } from "../security/authContext";
import { useNavigate } from "react-router-dom";

export const useLoginViewModel = () => {
    const { login, logout, isAuthenticated } = useAuth();
    const navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            setLoading(true);
            setError("");

            const data = await loginRequest(email, password);

            login(data.token);
            navigate("/");
        } catch (err) {
            if (err.error === "INACTIVE_USER") {
                setError("Konto nieaktywne. Sprawdź maila aktywacyjnego.");
            } else {
                setError("Nieprawidłowy email lub hasło");
            }
        }finally {
            setLoading(false);
        }
    };

    const handleLogout = () => {
        logout();
        navigate("/login");
    };

    return {
        email,
        password,
        loading,
        error,
        setEmail,
        setPassword,
        handleLogin,
        handleLogout,
        isAuthenticated
    };
};