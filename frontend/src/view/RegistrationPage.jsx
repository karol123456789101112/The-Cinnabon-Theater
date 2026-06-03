import React, { useState } from "react";
import { useAppUserViewModel } from "../viewmodel/useAppUserViewModel";
import ReCAPTCHA from "react-google-recaptcha";

export default function RegistrationPage() {
    const vm = useAppUserViewModel();

    const [recaptchaToken, setRecaptchaToken] = useState("");

    return (
        <div>
            <h1>App user info</h1>

            <input
                placeholder="First name"
                value={vm.form.firstName}
                onChange={e => vm.updateField("firstName", e.target.value)}
            />

            <input
                placeholder="Last name"
                value={vm.form.lastName}
                onChange={e => vm.updateField("lastName", e.target.value)}
            />

            <input
                placeholder="Email"
                value={vm.form.email}
                onChange={e => vm.updateField("email", e.target.value)}
            />

            <input
                placeholder="Telephone"
                value={vm.form.telephone}
                onChange={e => vm.updateField("telephone", e.target.value)}
            />

            <input
                type="password"
                placeholder="Password"
                value={vm.form.password}
                onChange={e => vm.updateField("password", e.target.value)}
            />

            <input
                type="password"
                placeholder="Confirm password"
                value={vm.form.confirmPassword}
                onChange={e => vm.updateField("confirmPassword", e.target.value)}
            />

            <ReCAPTCHA
                sitekey="6LcNEfksAAAAALRBy0oP3vWUAXnKWMzqVHQgJE4q"
                onChange={(token) => setRecaptchaToken(token || "")}
            />

            <button onClick={() => vm.submit(recaptchaToken)}>
                Register
            </button>

            {vm.loading && <p>Loading...</p>}
            {vm.error && <p style={{ color: "red" }}>{vm.error}</p>}
        </div>
    );
}