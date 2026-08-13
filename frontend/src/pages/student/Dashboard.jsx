import "./Dashboard.css";
import {
  Home,
  User,
  Zap,
  GraduationCap,
  Briefcase,
  Award,
  FileText,
  Search,
  ClipboardList,
  Settings,
  LogOut,
  Bell,
  ChevronDown,
  MapPin,
} from "lucide-react";

function Dashboard() {
  return (
    <div className="dashboard-page">
      {/* Sidebar */}
      <aside className="sidebar">
        <div className="logo">
          <div className="logo-icon">AI</div>
          <div>
            <h2>AI Career</h2>
            <span>Career Platform</span>
          </div>
        </div>

        <nav className="sidebar-menu">
          <a className="menu-item active">
            <Home size={18} />
            Dashboard
          </a>
          <a className="menu-item">
            <User size={18} />
            Profile
          </a>
          <a className="menu-item">
            <Zap size={18} />
            Skills
          </a>
          <a className="menu-item">
            <GraduationCap size={18} />
            Education
          </a>
          <a className="menu-item">
            <Briefcase size={18} />
            Projects
          </a>
          <a className="menu-item">
            <Award size={18} />
            Certifications
          </a>
          <a className="menu-item">
            <FileText size={18} />
            Resume
          </a>
          <a className="menu-item">
            <Search size={18} />
            Internships
          </a>
          <a className="menu-item">
            <ClipboardList size={18} />
            Applications
          </a>
        </nav>

        <div className="sidebar-bottom">
          <a className="menu-item">
            <Settings size={18} />
            Settings
          </a>
          <a className="menu-item logout">
            <LogOut size={18} />
            Logout
          </a>
        </div>
      </aside>

      {/* Main Area */}
      <main className="main-content">
        {/* Topbar */}
        <header className="topbar">
          <div className="mobile-title">AI Career</div>

          <div className="topbar-right">
            <button className="notification">
              <Bell size={18} />
              <span></span>
            </button>

            <div className="user-profile">
              <div className="avatar">S</div>
              <div>
                <strong>Soni Limbu</strong>
                <small>Student</small>
              </div>
              <ChevronDown size={16} />
            </div>
          </div>
        </header>

        {/* Dashboard Content */}
        <section className="dashboard-content">
          <div className="welcome-section">
            <div>
              <h1>Good Morning, Soni</h1>
              <p>Here's an overview of your career progress.</p>
            </div>
            <button className="edit-profile-btn">Edit Profile</button>
          </div>

          {/* Statistics */}
          <div className="stats-grid">
            <div className="stat-card">
              <div className="stat-icon purple">
                <Zap size={20} />
              </div>
              <div>
                <span>Skills</span>
                <h2>8</h2>
              </div>
              <small>+2 this month</small>
            </div>

            <div className="stat-card">
              <div className="stat-icon blue">
                <Briefcase size={20} />
              </div>
              <div>
                <span>Projects</span>
                <h2>4</h2>
              </div>
              <small>+1 this month</small>
            </div>

            <div className="stat-card">
              <div className="stat-icon green">
                <FileText size={20} />
              </div>
              <div>
                <span>Resume</span>
                <h2>80%</h2>
              </div>
              <small>Almost complete</small>
            </div>

            <div className="stat-card">
              <div className="stat-icon orange">
                <ClipboardList size={20} />
              </div>
              <div>
                <span>Applications</span>
                <h2>8</h2>
              </div>
              <small>3 shortlisted</small>
            </div>
          </div>

          {/* Progress Section */}
          <div className="two-column">
            <div className="card progress-card">
              <div className="card-header">
                <div>
                  <h3>Profile Completion</h3>
                  <p>Complete your profile to get better matches.</p>
                </div>
                <strong>85%</strong>
              </div>

              <div className="progress-bar">
                <div className="progress-fill" style={{ width: "85%" }}></div>
              </div>

              <div className="progress-footer">
                <span>Great progress!</span>
                <button>Complete Profile →</button>
              </div>
            </div>

            <div className="card progress-card">
              <div className="card-header">
                <div>
                  <h3>Career Readiness</h3>
                  <p>Your current career preparation.</p>
                </div>
                <strong>72%</strong>
              </div>

              <div className="progress-bar">
                <div className="progress-fill readiness" style={{ width: "72%" }}></div>
              </div>

              <div className="progress-footer">
                <span>Keep improving!</span>
                <button>View Skill Gaps →</button>
              </div>
            </div>
          </div>

          {/* Internship Section */}
          <div className="card">
            <div className="section-header">
              <div>
                <h3>Recommended Internships</h3>
                <p>Opportunities matched to your skills.</p>
              </div>
              <button>View All →</button>
            </div>

            <div className="internship-list">
              <div className="internship-item">
                <div className="company-logo">A</div>
                <div className="internship-info">
                  <h4>Frontend Developer Intern</h4>
                  <p>ABC Technologies</p>
                  <div className="tags">
                    <span>React</span>
                    <span>JavaScript</span>
                    <span>CSS</span>
                  </div>
                  <small>
                    <MapPin size={12} /> Kathmandu &nbsp;•&nbsp; 3 Months
                  </small>
                </div>
                <div className="match-score">
                  <strong>92%</strong>
                  <span>Match</span>
                  <button>View Details</button>
                </div>
              </div>

              <div className="internship-item">
                <div className="company-logo">T</div>
                <div className="internship-info">
                  <h4>React Developer Intern</h4>
                  <p>Tech Solutions Nepal</p>
                  <div className="tags">
                    <span>React</span>
                    <span>Git</span>
                    <span>REST API</span>
                  </div>
                  <small>
                    <MapPin size={12} /> Remote &nbsp;•&nbsp; 6 Months
                  </small>
                </div>
                <div className="match-score">
                  <strong>88%</strong>
                  <span>Match</span>
                  <button>View Details</button>
                </div>
              </div>
            </div>
          </div>

          {/* Bottom Section */}
          <div className="two-column">
            <div className="card">
              <div className="section-header">
                <div>
                  <h3>Application Status</h3>
                  <p>Your internship applications.</p>
                </div>
              </div>

              <div className="application-stats">
                <div>
                  <strong>8</strong>
                  <span>Applied</span>
                </div>
                <div>
                  <strong>3</strong>
                  <span>Shortlisted</span>
                </div>
                <div>
                  <strong>2</strong>
                  <span>Interview</span>
                </div>
                <div>
                  <strong>1</strong>
                  <span>Selected</span>
                </div>
              </div>
            </div>

            <div className="card">
              <div className="section-header">
                <div>
                  <h3>Skill Gap</h3>
                  <p>Skills you should improve.</p>
                </div>
                <button>View All →</button>
              </div>

              <div className="skill-gap">
                <div className="skill-row">
                  <span>TypeScript</span>
                  <div className="mini-progress">
                    <div style={{ width: "40%" }}></div>
                  </div>
                  <strong>40%</strong>
                </div>

                <div className="skill-row">
                  <span>Next.js</span>
                  <div className="mini-progress">
                    <div style={{ width: "30%" }}></div>
                  </div>
                  <strong>30%</strong>
                </div>

                <div className="skill-row">
                  <span>Testing</span>
                  <div className="mini-progress">
                    <div style={{ width: "50%" }}></div>
                  </div>
                  <strong>50%</strong>
                </div>
              </div>
            </div>
          </div>
        </section>
      </main>
    </div>
  );
}

export default Dashboard;