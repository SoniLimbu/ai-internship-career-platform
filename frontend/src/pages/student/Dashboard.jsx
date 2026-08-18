import { useNavigate } from "react-router-dom";
import {
  User,
  Code2,
  GraduationCap,
  FolderKanban,
  Award,
  FileText,
  Briefcase,
  Bell,
  Settings,
  LogOut,
  ChevronRight,
  MapPin,
  CheckCircle2,
  Lightbulb,
} from "lucide-react";
import "./Dashboard.css";

function Dashboard() {
  const navigate = useNavigate();

  const handleLogout = () => {
    // Authentication/API logout will be connected later.
    localStorage.removeItem("token");
    localStorage.removeItem("user");

    navigate("/login");
  };

  const menuItems = [
    { title: "Dashboard", icon: Briefcase, path: "/student/dashboard", active: true },
    { title: "My Profile", icon: User, path: "/student/profile" },
    { title: "My Skills", icon: Code2, path: "/student/skills" },
    { title: "Education", icon: GraduationCap, path: "/student/education" },
    { title: "Projects", icon: FolderKanban, path: "/student/projects" },
    { title: "Certifications", icon: Award, path: "/student/certifications" },
    { title: "Resume", icon: FileText, path: "/student/resume" },
  ];

  const quickActions = [
    {
      title: "My Profile",
      description: "Manage your personal information",
      icon: User,
      path: "/student/profile",
    },
    {
      title: "My Skills",
      description: "Add and manage your skills",
      icon: Code2,
      path: "/student/skills",
    },
    {
      title: "Education",
      description: "Manage your academic background",
      icon: GraduationCap,
      path: "/student/education",
    },
    {
      title: "Projects",
      description: "Showcase your projects",
      icon: FolderKanban,
      path: "/student/projects",
    },
    {
      title: "Certifications",
      description: "Manage your certificates",
      icon: Award,
      path: "/student/certifications",
    },
    {
      title: "Resume",
      description: "Upload and manage your resume",
      icon: FileText,
      path: "/student/resume",
    },
  ];

  return (
    <div className="dashboard-page">
      <aside className="dashboard-sidebar">
        <div className="dashboard-logo">
          <div className="logo-icon">
            <Briefcase size={22} />
          </div>
          <div>
            <h2>CareerAI</h2>
            <span>Student Portal</span>
          </div>
        </div>

        <nav className="dashboard-navigation">
          <p className="navigation-label">MAIN MENU</p>

          {menuItems.map((item) => {
            const Icon = item.icon;

            return (
              <button
                key={item.title}
                className={`dashboard-menu-item ${item.active ? "active" : ""}`}
                onClick={() => navigate(item.path)}
              >
                <Icon size={19} />
                <span>{item.title}</span>
                {item.active && <ChevronRight size={16} className="menu-arrow" />}
              </button>
            );
          })}

          <p className="navigation-label secondary">ACCOUNT</p>

          <button
            className="dashboard-menu-item"
            onClick={() => alert("Settings page coming soon.")}
          >
            <Settings size={19} />
            <span>Settings</span>
          </button>

          <button className="dashboard-menu-item logout-item" onClick={handleLogout}>
            <LogOut size={19} />
            <span>Logout</span>
          </button>
        </nav>

        <div className="sidebar-profile">
          <div className="sidebar-avatar">SL</div>
          <div className="sidebar-profile-info">
            <strong>Soni Limbu</strong>
            <span>Student</span>
          </div>
        </div>
      </aside>

      <main className="dashboard-main">
        <header className="dashboard-topbar">
          <div>
            <p className="dashboard-label">STUDENT DASHBOARD</p>
            <h1>Welcome back, Soni</h1>
            <p className="dashboard-subtitle">
              Manage your profile and prepare for your career journey.
            </p>
          </div>

          <div className="dashboard-topbar-actions">
            <button
              className="notification-button"
              onClick={() => alert("No new notifications.")}
              aria-label="Notifications"
            >
              <Bell size={20} />
              <span className="notification-dot"></span>
            </button>

            <button
              className="topbar-avatar"
              onClick={() => navigate("/student/profile")}
            >
              SL
            </button>
          </div>
        </header>

        {/* Profile summary */}
        <section className="profile-summary-card">
          <div className="profile-summary-left">
            <div className="dashboard-large-avatar">SL</div>

            <div>
              <h2>Soni Limbu</h2>
              <p className="student-program">BSc (Hons) Computing Student</p>
              <div className="student-location">
                <MapPin size={15} />
                Itahari, Nepal
              </div>
            </div>
          </div>

          <div className="profile-summary-right">
            <div className="completion-heading">
              <span>Profile Completion</span>
              <strong>80%</strong>
            </div>

            <div className="completion-progress">
              <div className="completion-progress-fill"></div>
            </div>

            <button
              className="complete-profile-button"
              onClick={() => navigate("/student/profile")}
            >
              Complete Profile
              <ChevronRight size={16} />
            </button>
          </div>
        </section>

        {/* Quick actions */}
        <section className="dashboard-section">
          <div className="section-heading">
            <div>
              <h2>Manage Your Career Profile</h2>
              <p>Keep your student and career information up to date.</p>
            </div>
          </div>

          <div className="quick-actions-grid">
            {quickActions.map((item) => {
              const Icon = item.icon;

              return (
                <button
                  key={item.title}
                  className="quick-action-card"
                  onClick={() => navigate(item.path)}
                >
                  <div className="quick-action-icon">
                    <Icon size={21} />
                  </div>

                  <div className="quick-action-content">
                    <h3>{item.title}</h3>
                    <p>{item.description}</p>
                  </div>

                  <ChevronRight size={18} className="quick-action-arrow" />
                </button>
              );
            })}
          </div>
        </section>

        {/* Profile status */}
        <section className="dashboard-bottom-grid">
          <div className="dashboard-card">
            <div className="dashboard-card-header">
              <div>
                <h2>Profile Status</h2>
                <p>Keep your profile complete.</p>
              </div>
              <CheckCircle2 size={23} className="status-check" />
            </div>

            <div className="status-list">
              <div className="status-item completed">
                <CheckCircle2 size={17} />
                <span>Basic information</span>
              </div>

              <div className="status-item completed">
                <CheckCircle2 size={17} />
                <span>Profile information</span>
              </div>

              <div className="status-item completed">
                <CheckCircle2 size={17} />
                <span>Skills</span>
              </div>

              <div className="status-item pending">
                <span className="pending-circle"></span>
                <span>Projects</span>
              </div>

              <div className="status-item pending">
                <span className="pending-circle"></span>
                <span>Certifications</span>
              </div>
            </div>
          </div>

          <div className="dashboard-card career-tip-card">
            <div className="career-tip-icon">
              <Lightbulb size={22} />
            </div>

            <div>
              <span className="career-tip-label">CAREER TIP</span>
              <h2>Build a strong profile</h2>
              <p>
                Students with complete profiles, relevant skills and projects
                can present themselves better to potential employers.
              </p>

              <button onClick={() => navigate("/student/profile")}>
                Improve My Profile
                <ChevronRight size={16} />
              </button>
            </div>
          </div>
        </section>
      </main>
    </div>
  );
}

export default Dashboard;