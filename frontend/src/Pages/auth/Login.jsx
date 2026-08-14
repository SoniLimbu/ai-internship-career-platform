import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Mail, Lock, Eye, EyeOff, ArrowRight } from "lucide-react";
import "./Auth.css";

function Login() {
  const navigate = useNavigate();

  const [showPassword, setShowPassword] = useState(false);
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });
  const [error, setError] = useState("");

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setError("");

    if (!formData.email || !formData.password) {
      setError("Please fill in all fields.");
      return;
    }

    // Backend authentication will be connected later.
    console.log("Login:", formData);

    navigate("/student/dashboard");
  };

  return (
    <div className="auth-page">
      <div className="auth-left">
        <div className="auth-brand">
          <div className="brand-icon">C</div>
          <span>CareerConnect</span>
        </div>

        <div className="auth-hero">
          <p className="eyebrow">YOUR CAREER JOURNEY STARTS HERE</p>

          <h1>
            Build your future.
            <span> Find your path.</span>
          </h1>

          <p className="hero-text">
            Connect your skills, education and experience in one place and
            discover opportunities that match your career goals.
          </p>

          <div className="hero-stats">
            <div>
              <strong>10K+</strong>
              <span>Students</span>
            </div>
            <div>
              <strong>500+</strong>
              <span>Opportunities</span>
            </div>
            <div>
              <strong>95%</strong>
              <span>Success Rate</span>
            </div>
          </div>
        </div>
      </div>

      <div className="auth-right">
        <div className="auth-card">
          <div className="mobile-brand">
            <div className="brand-icon">C</div>
            <span>CareerConnect</span>
          </div>

          <div className="auth-heading">
            <h2>Welcome back</h2>
            <p>Sign in to continue your career journey.</p>
          </div>

          {error && <div className="auth-error">{error}</div>}

          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label>Email address</label>

              <div className="input-wrapper">
                <Mail size={19} />
                <input
                  type="email"
                  name="email"
                  placeholder="you@example.com"
                  value={formData.email}
                  onChange={handleChange}
                />
              </div>
            </div>

            <div className="form-group">
              <div className="label-row">
                <label>Password</label>
                <a href="#forgot">Forgot password?</a>
              </div>

              <div className="input-wrapper">
                <Lock size={19} />
                <input
                  type={showPassword ? "text" : "password"}
                  name="password"
                  placeholder="Enter your password"
                  value={formData.password}
                  onChange={handleChange}
                />
                <button
                  type="button"
                  className="password-toggle"
                  onClick={() => setShowPassword(!showPassword)}
                >
                  {showPassword ? <EyeOff size={19} /> : <Eye size={19} />}
                </button>
              </div>
            </div>

            <label className="remember">
              <input type="checkbox" />
              <span>Remember me</span>
            </label>

            <button className="auth-button" type="submit">
              Sign in
              <ArrowRight size={19} />
            </button>
          </form>

          <div className="auth-divider">
            <span>OR</span>
          </div>

          <p className="auth-switch">
            Don't have an account? <Link to="/register">Create an account</Link>
          </p>
        </div>
      </div>
    </div>
  );
}

export default Login;