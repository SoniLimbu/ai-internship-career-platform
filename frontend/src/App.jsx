import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import Login from "./Pages/auth/Login";
import Register from "./Pages/auth/Register";

import Dashboard from "./Pages/student/Dashboard";
import StudentProfile from "./Pages/student/StudentProfile";
import Skills from "./Pages/student/Skills";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Navigate to="/login" replace />} />

        {/* Authentication */}
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />

        {/* Student module */}
        <Route path="/student/dashboard" element={<Dashboard />} />
        <Route path="/student/profile" element={<StudentProfile />} />
        <Route path="/student/skills" element={<Skills />} />

        {/* Unknown URL */}
        <Route path="*" element={<Navigate to="/login" replace />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;