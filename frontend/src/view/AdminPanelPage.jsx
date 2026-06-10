import {useAdminViewModel} from "../viewmodel/useAdminViewModel";

export default function AdminPanelPage() {

    const vm = useAdminViewModel();

    return(
        <div>
            {vm.allUsers?.map((user) => (
                <div key={user.id}>
                    {user.id} {user.email} {user.firstName} {user.lastName} <br />
                </div>
            ))}
        </div>
    );
}