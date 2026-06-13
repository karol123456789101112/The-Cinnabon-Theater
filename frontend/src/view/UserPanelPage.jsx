import React from "react";
import {useParams} from "react-router-dom";
import {useAppUserViewModel} from "../viewmodel/useAppUserViewModel";

export default function UserPanelPage() {

    const { id: userId } = useParams();
    const vm = useAppUserViewModel();

    return (
        <div>
            <h1>App user info</h1>

            <button onClick={() => vm.startEditing()}>
                Edytuj dane
            </button>

            {vm.showEditForm && (
                <>
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

                    <button onClick={() => vm.submitEdit()}>
                        Save
                    </button>

                    <button onClick={vm.cancelEditing}>
                        Cancel
                    </button>
                </>
            )}

            {vm.loading && <p>Loading...</p>}
            {vm.error && <p style={{ color: "red" }}>{vm.error}</p>}
        </div>
    );
}