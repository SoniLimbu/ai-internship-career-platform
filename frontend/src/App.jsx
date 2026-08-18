import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import Login from "./Pages/auth/Login";
import Register from "./Pages/auth/Register";

import Dashboard from "./Pages/student/Dashboard";
import StudentProfile from "./Pages/student/StudentProfile";
import Skills from "./Pages/student/Skills";
import Certification from "./Pages/student/Certification";

function App() {
  return (
    <BrowserRouter>
      <Routes>

        {/* =========================
            DEFAULT ROUTE
        ========================= */}
        <Route
          path="/"
          element={<Navigate to="/login" replace />}
        />

        {/* =========================
            AUTHENTICATION
        ========================= */}
        <Route
          path="/login"
          element={<Login />}
        />

        <Route
          path="/register"
          element={<Register />}
        />

        {/* =========================
            STUDENT MODULE
        ========================= */}

        {/* Student Dashboard */}
        <Route
          path="/student/dashboard"
          element={<Dashboard />}
        />

        {/* Student Profile */}
        <Route
          path="/student/profile"
          element={<StudentProfile />}
        />

        {/* Student Skills */}
        <Route
          path="/student/skills"
          element={<Skills />}
        />

        {/* Student Certifications */}
        <Route
          path="/student/certifications"
          element={<Certification />}
        />

        {/* =========================
            UNKNOWN URL
        ========================= */}
        <Route
          path="*"
          element={<Navigate to="/login" replace />}
        />

      </Routes>
    </BrowserRouter>
  );
}

export default App;