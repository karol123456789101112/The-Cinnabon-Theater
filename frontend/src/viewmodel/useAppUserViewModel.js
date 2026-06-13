import { useState } from "react";
import {getUser, addUser, editUser} from "../model/userApi";

export function useAppUserViewModel() {
    const [form, setForm] = useState({
        firstName: "",
        lastName: "",
        email: "",
        telephone: "",
        password: "",
        confirmPassword: ""
    });
    const [showEditForm, setShowEditForm] = useState(false);
    const [originalUser, setOriginalUser] = useState(null);
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

    async function submitEdit() {
        setLoading(true);
        setError(null);

        try {
            const formData = { ...form };

            if (!formData.password) {
                delete formData.password;
                delete formData.confirmPassword;
            }

            await editUser(form);
        } catch (e) {
            setError("Błąd podczas wysyłania formularza");
        } finally {
            setLoading(false);
        }
    }

    async function startEditing() {
        setLoading(true);
        setError(null);

        try {
            const user = await getUser();

            setOriginalUser(user);

            setForm({
                firstName: user.firstName,
                lastName: user.lastName,
                email: user.email,
                telephone: user.telephone ?? "",
                password: "",
                confirmPassword: ""
            });

            setShowEditForm(true);
        } catch (e) {
            setError("Nie udało się pobrać użytkownika");
        } finally {
            setLoading(false);
        }
    }

    function cancelEditing() {
        if (originalUser) {
            setForm({
                firstName: originalUser.firstName,
                lastName: originalUser.lastName,
                email: originalUser.email,
                telephone: originalUser.telephone ?? "",
                password: "",
                confirmPassword: ""
            });
        }

        setShowEditForm(false);
    }

    return {
        form,
        loading,
        showEditForm,
        error,
        updateField,
        submit,
        submitEdit,
        startEditing,
        cancelEditing
    };
}