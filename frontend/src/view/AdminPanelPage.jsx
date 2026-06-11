import {useAdminViewModel} from "../viewmodel/useAdminViewModel";

export default function AdminPanelPage() {

    const vm = useAdminViewModel();

    return(
        <div>
            {vm.allUsers.map((user) => (
                <div key={user.id}>
                    {user.id} {user.email} {user.firstName} {user.lastName} {user.role}
                    <button onClick={() => vm.handleDeleteUser(user.id)}>Usuń</button>
                    <button onClick={() => vm.handleToggleRole(user.id)}>Zmień role</button>
                </div>
            ))}
            {vm.allMovieScreenings.map((screening) => (
                <div key={screening.id}>
                    {screening.id} {screening.startTime.replace("T", " ")}
                    <button onClick={() => vm.handleDeleteMovieScreening(screening.id)}>Usuń</button>
                </div>
            ))}
            <input
                placeholder="Name of the movie"
                value={vm.addMovieForm.name}
                onChange={e => vm.updateField("name", e.target.value)}
            />
            <input
                placeholder="Duration of the movie"
                value={vm.addMovieForm.duration}
                onChange={e => vm.updateField("duration", e.target.value)}
            />
            <select
                multiple
                value={vm.addMovieForm.genreIds}
                onChange={(e) => {
                    const values = Array.from(
                        e.target.selectedOptions,
                        option => Number(option.value)
                    );

                    vm.updateField("genreIds", values);
                }}
            >
                {vm.allGenres.map(g => (
                    <option key={g.id} value={g.id}>
                        {g.name}
                    </option>
                ))}
            </select>

            <button
                onClick={() => vm.submitAddMovieForm()}
            >
                Add movie
            </button>

            {vm.allMovies.map((movie) => (
                <div key={movie.id}>
                    {movie.id} {movie.name} {movie.duration}
                    <button onClick={() => vm.handleDeleteMovie(movie.id)}>Usuń</button>
                </div>
            ))}
        </div>
    );
}