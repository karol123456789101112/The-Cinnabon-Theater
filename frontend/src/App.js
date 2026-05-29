import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import RegisterPage from './model/RegistrationPage';

function AppContent() {

  return (
      <Routes>
        <Route path="/register" element={<RegisterPage />} />
      </Routes>
  );
}

export default function App() {
  return (
        <Router>
          <AppContent />
        </Router>
  );
}
