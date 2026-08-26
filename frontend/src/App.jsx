import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

// Authentication
import Login from "./Pages/auth/Login";
import Register from "./Pages/auth/Register";

// Student
import Dashboard from "./Pages/student/Dashboard";
import StudentProfile from "./Pages/student/StudentProfile";
import Skills from "./Pages/student/Skills";
import Certification from "./Pages/student/Certification";
import Resume from "./Pages/student/Resume";

function App() {
  return (
    <BrowserRouter>
      <Routes>

        {/* =========================
            DEFAULT
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

        {/* Dashboard */}
        <Route
          path="/student/dashboard"
          element={<Dashboard />}
        />

        {/* Profile */}
        <Route
          path="/student/profile"
          element={<StudentProfile />}
        />

        {/* Skills */}
        <Route
          path="/student/skills"
          element={<Skills />}
        />

        {/* Certifications */}
        <Route
          path="/student/certifications"
          element={<Certification />}
        />

        {/* Resume */}
        <Route
          path="/student/resume"
          element={<Resume />}
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