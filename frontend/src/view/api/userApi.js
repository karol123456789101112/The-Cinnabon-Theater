export async function addUser(userDto, recaptchaToken) {
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

    const text = await res.text();

    if (!res.ok) {
        throw new Error(`HTTP ${res.status}: ${text}`);
    }

    return text;
}