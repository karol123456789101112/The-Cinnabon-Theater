export const addUser = async (userDto, recaptchaToken) => {
    const res = await fetch("http://localhost:8081/api/users/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            ...userDto,
            recaptchaToken
        })
    });

    const data = await res.text();

    if (!res.ok) {
        throw new Error(`HTTP ${res.status}: ${data}`);
    }

    return data;
};

export const editUser = async (form) => {
    const token = localStorage.getItem('token');

    const formData = {
        email: form.email,
        firstName: form.firstName,
        lastName: form.lastName,
        telephone: form.telephone
    };

    if (form.password && form.password.trim() !== "") {
        formData.password = form.password;
        formData.confirmPassword = form.confirmPassword;
    }

    const response = await fetch("http://localhost:8081/api/users/edit/me", {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(formData)
    });

    if (!response.ok) {
        throw new Error("Failed to edit user");
    }

    return response.json();
};

export const getUser = async () => {
    const token = localStorage.getItem('token');
    const response = await fetch(`http://localhost:8081/api/users/me`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    })

    if (!response.ok) {
        throw new Error("Failed to edit user")
    }

    return response.json();
}