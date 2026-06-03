import { useState } from "react";
import { addUser } from "../model/userApi";

export function useAppUserViewModel() {
    const [form, setForm] = useState({
        firstName: "",
        lastName: "",
        email: "",
        telephone: "",
        password: "",
        confirmPassword: ""
    });

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    function updateField(field, value) {
        setForm(prev => ({
            ...prev,
            [field]: value
        }));
    }

    async function submit(recaptchaToken) {
        setLoading(true);
        setError(null);

        try {
            await addUser(form, recaptchaToken);
        } catch (e) {
            setError("Błąd podczas wysyłania formularza");
        } finally {
            setLoading(false);
        }
    }

    return {
        form,
        loading,
        error,
        updateField,
        submit
    };
}