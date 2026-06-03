import { useLoginViewModel } from "../viewmodel/useLoginViewModel";

export default function LoginPage() {
    const vm = useLoginViewModel();

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-4">

                    <div className="card shadow">
                        <div className="card-body">

                            <h2 className="text-center mb-4">
                                Logowanie
                            </h2>

                            {vm.error && (
                                <div className="alert alert-danger">
                                    {vm.error}
                                </div>
                            )}

                            <form onSubmit={vm.handleLogin}>

                                <div className="mb-3">
                                    <label>Email</label>

                                    <input
                                        type="email"
                                        className="form-control"
                                        value={vm.email}
                                        onChange={(e) =>
                                            vm.setEmail(e.target.value)
                                        }
                                    />
                                </div>

                                <div className="mb-3">
                                    <label>Hasło</label>

                                    <input
                                        type="password"
                                        className="form-control"
                                        value={vm.password}
                                        onChange={(e) =>
                                            vm.setPassword(e.target.value)
                                        }
                                    />
                                </div>

                                <button
                                    className="btn btn-primary w-100"
                                    disabled={vm.loading}
                                >
                                    {vm.loading
                                        ? "Logowanie..."
                                        : "Zaloguj"}
                                </button>

                            </form>

                            {vm.isAuthenticated && (
                                <button
                                    className="btn btn-danger w-100 mt-3"
                                    onClick={vm.handleLogout}
                                >
                                    Wyloguj
                                </button>
                            )}

                        </div>
                    </div>

                </div>
            </div>
        </div>
    );
}