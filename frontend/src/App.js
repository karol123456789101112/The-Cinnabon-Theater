import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import RegisterPage from './view/RegistrationPage';
import LoginPage from './view/LoginPage';
import { AuthProvider, useAuth } from './security/authContext';
import { Navigate } from 'react-router-dom';
import MovieScreeningsPage from "./view/MovieScreeningsPage";

function AppContent() {

    const { isAuthenticated, loading, userRole } = useAuth();

    if (loading) return <div>Loading...</div>;

  return (
      <Routes>
          <Route path="/register" element={isAuthenticated ?
                   <RegisterPage />
                 : <Navigate to="/" />
            }
          />

          <Route path="/login" element={<LoginPage />} />
          <Route path="/" element={<MovieScreeningsPage />} />
          <Route path="*" element={<Navigate to="/" />} />
      </Routes>
  );
}

export default function App() {
  return (
      <AuthProvider>
        <Router>
          <AppContent />
        </Router>
      </AuthProvider>
  );
}
