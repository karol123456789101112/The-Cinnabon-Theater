import {useAdminViewModel} from "../viewmodel/useAdminViewModel";
import {Link} from "react-router-dom";

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
            <input
                placeholder="Price"
                value={vm.addMovieScreeningForm.price}
                onChange={e => vm.updateAddMovieScreeningField("price", e.target.value)}
            />
            <input
                placeholder="Start time"
                value={vm.addMovieScreeningForm.startTime}
                onChange={e => vm.updateAddMovieScreeningField("startTime", e.target.value)}
            />
            <select
                value={vm.addMovieScreeningForm.movieId}
                onChange={(e) => {
                    vm.updateAddMovieScreeningField("movieId", Number(e.target.value));
                }}
            >
                {vm.allMovies.map(m => (
                    <option key={m.id} value={m.id}>
                        {m.name}
                    </option>
                ))}
            </select>
            <select
                value={vm.addMovieScreeningForm.screeningRoomId}
                onChange={(e) => {
                    vm.updateAddMovieScreeningField("screeningRoomId", Number(e.target.value));
                }}
            >
                {vm.allScreeningRooms.map(sr => (
                    <option key={sr.id} value={sr.id}>
                        {sr.roomNumber}
                    </option>
                ))}
            </select>
            <button
                onClick={() => vm.submitAddMovieScreeningForm()}
            >
            Add movie screening
            </button>
            {vm.allMovieScreenings.map((screening) => (
                <div key={screening.id}>
                    {screening.id} {screening.startTime.replace("T", " ")}

                    <button onClick={() => vm.handleDeleteMovieScreening(screening.id)}>
                        Usuń
                    </button>

                    <button onClick={() => vm.startEditingMovieScreening(screening)}>
                        Edytuj
                    </button>

                    {vm.editingMovieScreeningId === screening.id && (
                        <div>
                            <input
                                value={vm.editMovieScreeningForm.price}
                                onChange={e =>
                                    vm.updateEditMovieScreeningField(
                                        "price",
                                        e.target.value
                                    )
                                }
                            />

                            <input
                                value={vm.editMovieScreeningForm.startTime}
                                onChange={e =>
                                    vm.updateEditMovieScreeningField(
                                        "startTime",
                                        e.target.value
                                    )
                                }
                            />

                            <select
                                value={vm.editMovieScreeningForm.movieId}
                                onChange={e =>
                                    vm.updateEditMovieScreeningField(
                                        "movieId",
                                        Number(e.target.value)
                                    )
                                }
                            >
                                {vm.allMovies.map(m => (
                                    <option key={m.id} value={m.id}>
                                        {m.name}
                                    </option>
                                ))}
                            </select>

                            <select
                                value={vm.editMovieScreeningForm.screeningRoomId}
                                onChange={e =>
                                    vm.updateEditMovieScreeningField(
                                        "screeningRoomId",
                                        Number(e.target.value)
                                    )
                                }
                            >
                                {vm.allScreeningRooms.map(sr => (
                                    <option key={sr.id} value={sr.id}>
                                        {sr.roomNumber}
                                    </option>
                                ))}
                            </select>

                            <button onClick={vm.submitMovieScreeningForm}>
                                Zapisz
                            </button>

                            <button onClick={vm.cancelEditingMovieScreening}>
                                Anuluj
                            </button>
                        </div>
                    )}
                </div>
            ))}
            <input
                placeholder="Name of the movie"
                value={vm.addMovieForm.name}
                onChange={e => vm.updateAddMovieField("name", e.target.value)}
            />
            <input
                placeholder="Duration of the movie"
                value={vm.addMovieForm.duration}
                onChange={e => vm.updateAddMovieField("duration", e.target.value)}
            />
            <select
                multiple
                value={vm.addMovieForm.genreIds}
                onChange={(e) => {
                    const values = Array.from(
                        e.target.selectedOptions,
                        option => Number(option.value)
                    );

                    vm.updateAddMovieField("genreIds", values);
                }}
            >
                {vm.allGenres.map(g => (
                    <option key={g.id} value={g.id}>
                        {g.name}
                    </option>
                ))}
            </select>

            <button
                onClick={() => vm.submitMovieForm()}
            >
                Add movie
            </button>

            {vm.allMovies.map((movie) => (
                <div key={movie.id}>
                    {movie.id} {movie.name} {movie.duration}

                    <button onClick={() => vm.handleDeleteMovie(movie.id)}>
                        Usuń
                    </button>

                    <button onClick={() => vm.startEditing(movie)}>
                        Edytuj
                    </button>

                    {vm.editingMovieId === movie.id && (
                        <div>
                            <input
                                value={vm.editMovieForm.name}
                                onChange={e => vm.updateEditMovieField("name", e.target.value)}
                            />

                            <input
                                value={vm.editMovieForm.duration}
                                onChange={e => vm.updateEditMovieField("duration", e.target.value)}
                            />

                            <select
                                multiple
                                value={vm.editMovieForm.genreIds}
                                onChange={(e) => {
                                    const values = Array.from(
                                        e.target.selectedOptions,
                                        option => Number(option.value)
                                    );

                                    vm.updateEditMovieField("genreIds", values);
                                }}

                            >

                            {vm.allGenres.map(g => (
                                <option key={g.id} value={g.id}>
                                    {g.name}
                                </option>
                            ))}

                            </select>

                            <button onClick={vm.submitMovieForm}>
                                Zapisz
                            </button>

                            <button onClick={vm.cancelEditing}>
                                Anuluj
                            </button>
                        </div>
                    )}
                </div>
            ))}
        </div>
    );
}