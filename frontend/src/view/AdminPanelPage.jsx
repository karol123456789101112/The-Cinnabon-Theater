import {useAdminViewModel} from "../viewmodel/useAdminViewModel";

export default function AdminPanelPage() {

    const vm = useAdminViewModel();

    return(
        <div>
            {vm.allUsers?.map((user) => (
                <div key={user.id}>
                    {user.id} {user.email} {user.firstName} {user.lastName} {user.role} <br />
                    <button onClick={() => vm.handleDeleteUser(user.id)}>Usuń użytkownika</button>
                    <button onClick={() => vm.handleToggleRole(user.id)}>Zmień role</button>
                </div>
            ))}
        </div>
    );
}