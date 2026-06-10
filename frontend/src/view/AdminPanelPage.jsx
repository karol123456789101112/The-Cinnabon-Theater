import {useAdminViewModel} from "../viewmodel/useAdminViewModel";

export default function AdminPanelPage() {

    const vm = useAdminViewModel();

    return(
        <div>
            {vm.allUsers.map((user) => (
                <div key={user.id}>
                    {user.id} {user.email} {user.firstName} {user.lastName} {user.role}
                    <button onClick={() => vm.handleDeleteUser(user.id)}>Usuń użytkownika</button>
                    <button onClick={() => vm.handleToggleRole(user.id)}>Zmień role</button>
                </div>
            ))}
            {vm.allMovieScreenings.map((screening) => (
                <div key={screening.id}>
                    {screening.id} {screening.startTime.replace("T", " ")} <br />
                </div>
            ))}
            {vm.allMovies.map((movie) => (
                <div key={movie.id}>
                    {movie.id} {movie.name} {movie.duration} <br />
                </div>
            ))}
        </div>
    );
}