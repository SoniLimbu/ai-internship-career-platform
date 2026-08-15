import { useState } from "react";
import {
  User,
  Mail,
  Phone,
  MapPin,
  Github,
  Linkedin,
  Edit3,
  Save,
  X,
} from "lucide-react";
import "./StudentProfile.css";

function StudentProfile() {
  const [isEditing, setIsEditing] = useState(false);

  const [formData, setFormData] = useState({
    name: "Soni Limbu",
    email: "soni@example.com",
    phone: "98XXXXXXXX",
    location: "Itahari, Nepal",
    bio: "Computing student interested in frontend development, UI/UX design and building useful digital products.",
    education: "BSc (Hons) Computing",
    skills: "React, JavaScript, HTML, CSS",
    github: "https://github.com/SoniLimbu",
    linkedin: "https://linkedin.com/",
  });
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleEdit = () => {
    setMessage("");
    setIsEditing(true);
  };

  const handleCancel = () => {
    setMessage("");
    setIsEditing(false);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    /*
      API connection will be added when the Java backend
      endpoints are ready.
    */

    setMessage("Profile updated successfully!");
    setIsEditing(false);
  };

  return (
    <div className="student-profile-page">
      {/* Page header */}
      <div className="student-profile-header">
        <div>
          <p className="profile-label">STUDENT PROFILE</p>
          <h1>My Profile</h1>
          <p className="profile-description">
            Manage your personal information and career profile.
          </p>
        </div>

        {!isEditing && (
          <button className="edit-profile-button" onClick={handleEdit}>
            <Edit3 size={17} />
            Edit Profile
          </button>
        )}
      </div>

      {message && <div className="profile-success-message">{message}</div>}

      {isEditing ? (
        /* Edit mode */
        <form className="student-profile-card" onSubmit={handleSubmit}>
          <div className="profile-card-header">
            <div>
              <h2>Edit Profile</h2>
              <p>Update your personal and career information.</p>
            </div>
          </div>

          <div className="profile-avatar-section">
            <div className="student-avatar">SL</div>
            <div>
              <h3>{formData.name}</h3>
              <p>Student</p>
            </div>
          </div>

          <div className="profile-form-grid">
            <div className="profile-input-group">
              <label>Full Name</label>
              <input
                type="text"
                name="name"
                value={formData.name}
                onChange={handleChange}
                required
              />
            </div>

            <div className="profile-input-group">
              <label>Email Address</label>
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                required
              />
            </div>

            <div className="profile-input-group">
              <label>Phone Number</label>
              <input
                type="text"
                name="phone"
                value={formData.phone}
                onChange={handleChange}
              />
            </div>

            <div className="profile-input-group">
              <label>Location</label>
              <input
                type="text"
                name="location"
                value={formData.location}
                onChange={handleChange}
              />
            </div>

            <div className="profile-input-group full-width">
              <label>Education</label>
              <input
                type="text"
                name="education"
                value={formData.education}
                onChange={handleChange}
              />
            </div>

            <div className="profile-input-group full-width">
              <label>About Me</label>
              <textarea
                name="bio"
                rows="5"
                value={formData.bio}
                onChange={handleChange}
              />
            </div>

            <div className="profile-input-group full-width">
              <label>Skills</label>
              <input
                type="text"
                name="skills"
                value={formData.skills}
                onChange={handleChange}
                placeholder="React, JavaScript, Python..."
              />
            </div>

            <div className="profile-input-group">
              <label>GitHub URL</label>
              <input
                type="url"
                name="github"
                value={formData.github}
                onChange={handleChange}
              />
            </div>

            <div className="profile-input-group">
              <label>LinkedIn URL</label>
              <input
                type="url"
                name="linkedin"
                value={formData.linkedin}
                onChange={handleChange}
              />
            </div>
          </div>

          <div className="profile-form-actions">
            <button
              type="button"
              className="cancel-profile-button"
              onClick={handleCancel}
            >
              <X size={17} />
              Cancel
            </button>

            <button type="submit" className="save-profile-button">
              <Save size={17} />
              Save Changes
            </button>
          </div>
        </form>
      ) : (
        /* View mode */
        <div className="student-profile-layout">
          <div className="student-profile-card">
            <div className="profile-main">
              <div className="student-avatar large">SL</div>

              <div className="profile-main-info">
                <h2>{formData.name}</h2>
                <p>BSc (Hons) Computing Student</p>

                <div className="profile-location">
                  <MapPin size={15} />
                  {formData.location}
                </div>
              </div>
            </div>

            <div className="profile-divider"></div>

            <section className="profile-section">
              <h3>About Me</h3>
              <p>{formData.bio}</p>
            </section>

            <div className="profile-divider"></div>

            <section className="profile-section">
              <h3>Education</h3>

              <div className="education-box">
                <div className="education-icon">
                  <User size={18} />
                </div>

                <div>
                  <strong>{formData.education}</strong>
                  <p>Student</p>
                </div>
              </div>
            </section>

            <div className="profile-divider"></div>

            <section className="profile-section">
              <h3>Skills</h3>

              <div className="skills-container">
                {formData.skills.split(",").map((skill, index) => (
                  <span key={index}>{skill.trim()}</span>
                ))}
              </div>
            </section>
          </div>

          <div className="profile-sidebar">
            <div className="profile-side-card">
              <h3>Contact Information</h3>

              <div className="profile-contact">
                <Mail size={18} />
                <div>
                  <span>Email</span>
                  <p>{formData.email}</p>
                </div>
              </div>

              <div className="profile-contact">
                <Phone size={18} />
                <div>
                  <span>Phone</span>
                  <p>{formData.phone}</p>
                </div>
              </div>
            </div>

            <div className="profile-side-card">
              <h3>Social Profiles</h3>

              <a
                href={formData.github}
                target="_blank"
                rel="noreferrer"
                className="profile-social"
              >
                <Github size={20} />
                <div>
                  <span>GitHub</span>
                  <p>View GitHub profile</p>
                </div>
              </a>

              <a
                href={formData.linkedin}
                target="_blank"
                rel="noreferrer"
                className="profile-social"
              >
                <Linkedin size={20} />
                <div>
                  <span>LinkedIn</span>
                  <p>View LinkedIn profile</p>
                </div>
              </a>
            </div>

            <div className="profile-side-card">
              <div className="completion-title">
                <h3>Profile Completion</h3>
                <strong>80%</strong>
              </div>

              <div className="completion-bar">
                <div></div>
              </div>

              <p className="completion-text">
                Complete your profile to improve your internship and career
                opportunities.
              </p>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default StudentProfile;