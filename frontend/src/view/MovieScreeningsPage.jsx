import { Link } from "react-router-dom";
import { useMovieScreeningViewModel } from "../viewmodel/useMovieScreeningViewModel";

export default function MovieScreeningsPage() {
    const vm = useMovieScreeningViewModel();

    return (
        <div>
            {vm.movieScreenings.map(day => (
                <div key={day.date}>
                    <h2>{day.date}</h2>

                    {day.movies.map(movie => (
                        <div key={movie.movieId} style={{ marginLeft: "20px" }}>
                            <h3>{movie.title}</h3>
                            <h3>Length: {movie.duration} minutes</h3>
                            <div>
                                {movie.screenings.map(screening => (
                                    <Link
                                        key={screening.id}
                                        to={`/screenings/${screening.id}`}
                                        style={{
                                            marginRight: "10px",
                                            textDecoration: "none",
                                            color: "blue"
                                        }}
                                    >
                                        {screening.time.slice(0, 5)}
                                    </Link>
                                ))}
                            </div>
                        </div>
                    ))}
                </div>
            ))}
        </div>
    );
}