import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import RegisterPage from './view/RegistrationPage';
import LoginPage from './view/LoginPage';
import { AuthProvider, useAuth } from './security/authContext';
import { Navigate } from 'react-router-dom';
import MovieScreeningsPage from "./view/MovieScreeningsPage";
import ReservationPage from "./view/ReservationPage";
import PaymentPage from "./view/PaymentPage";
import AdminPanelPage from "./view/AdminPanelPage";

function AppContent() {

    const { isAuthenticated, loading, userRole } = useAuth();

    if (loading) return <div>Loading...</div>;

  return (
      <Routes>
          <Route path="/payment" element={isAuthenticated ?
                   <PaymentPage />
                 : <Navigate to="/login" />
            }
          />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/" element={<MovieScreeningsPage />} />
          <Route path="/screenings/:id" element={<ReservationPage />} />
          <Route path="/adminPanel" element={<AdminPanelPage />} />
          <Route path="*" element={<MovieScreeningsPage />} />
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
