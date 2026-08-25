import { useState } from "react";
import {
  User,
  Mail,
  Phone,
  MapPin,
  Github,
  Linkedin,
  GraduationCap,
  Award,
  BriefcaseBusiness,
  Code2,
  Edit3,
  Save,
  X,
  Download,
  Plus,
  Trash2,
} from "lucide-react";

import "./Resume.css";

function Resume() {
  const [isEditing, setIsEditing] = useState(false);

  const [resumeData, setResumeData] = useState({
    name: "Soni Limbu",
    title: "BSc (Hons) Computing Student",
    email: "soni@example.com",
    phone: "98XXXXXXXX",
    location: "Itahari, Nepal",
    github: "https://github.com/SoniLimbu",
    linkedin: "https://linkedin.com/",
    summary:
      "Computing student passionate about frontend development, UI/UX design and building useful digital products. Interested in developing modern web applications and continuously improving technical skills.",
    education: [
      {
        id: 1,
        degree: "BSc (Hons) Computing",
        institution: "Itahari International College",
        year: "2024 - Present",
        description:
          "Studying computing with a focus on software development, web technologies and databases.",
      },
    ],
    skills: [
      "React",
      "JavaScript",
      "HTML",
      "CSS",
      "Django",
      "Python",
      "Git",
      "GitHub",
    ],
    projects: [
      {
        id: 1,
        name: "AI Internship & Career Platform",
        description:
          "A platform designed to help university students manage their career profiles, skills, education, projects and internship opportunities.",
        technologies: "React, Django, PostgreSQL",
      },
      {
        id: 2,
        name: "Weather App",
        description:
          "A responsive weather application that displays weather information using an external weather API.",
        technologies: "HTML, CSS, JavaScript, Weather API",
      },
    ],
    certifications: [
      {
        id: 1,
        name: "Google UX Design Certificate",
        organization: "Google",
        year: "2026",
      },
    ],
  });

  const [message, setMessage] = useState("");

  const handlePersonalChange = (e) => {
    setResumeData({
      ...resumeData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSummaryChange = (e) => {
    setResumeData({
      ...resumeData,
      summary: e.target.value,
    });
  };

  const handleEducationChange = (id, field, value) => {
    setResumeData({
      ...resumeData,
      education: resumeData.education.map((item) =>
        item.id === id
          ? {
              ...item,
              [field]: value,
            }
          : item
      ),
    });
  };

  const handleProjectChange = (id, field, value) => {
    setResumeData({
      ...resumeData,
      projects: resumeData.projects.map((item) =>
        item.id === id
          ? {
              ...item,
              [field]: value,
            }
          : item
      ),
    });
  };

  const handleCertificationChange = (id, field, value) => {
    setResumeData({
      ...resumeData,
      certifications: resumeData.certifications.map((item) =>
        item.id === id
          ? {
              ...item,
              [field]: value,
            }
          : item
      ),
    });
  };

  const handleSkillsChange = (e) => {
    const skills = e.target.value
      .split(",")
      .map((skill) => skill.trim())
      .filter((skill) => skill !== "");

    setResumeData({
      ...resumeData,
      skills,
    });
  };

  const addEducation = () => {
    setResumeData({
      ...resumeData,
      education: [
        ...resumeData.education,
        {
          id: Date.now(),
          degree: "",
          institution: "",
          year: "",
          description: "",
        },
      ],
    });
  };

  const deleteEducation = (id) => {
    setResumeData({
      ...resumeData,
      education: resumeData.education.filter(
        (item) => item.id !== id
      ),
    });
  };

  const addProject = () => {
    setResumeData({
      ...resumeData,
      projects: [
        ...resumeData.projects,
        {
          id: Date.now(),
          name: "",
          description: "",
          technologies: "",
        },
      ],
    });
  };

  const deleteProject = (id) => {
    setResumeData({
      ...resumeData,
      projects: resumeData.projects.filter(
        (item) => item.id !== id
      ),
    });
  };

  const addCertification = () => {
    setResumeData({
      ...resumeData,
      certifications: [
        ...resumeData.certifications,
        {
          id: Date.now(),
          name: "",
          organization: "",
          year: "",
        },
      ],
    });
  };

  const deleteCertification = (id) => {
    setResumeData({
      ...resumeData,
      certifications: resumeData.certifications.filter(
        (item) => item.id !== id
      ),
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

  const handleSave = (e) => {
    e.preventDefault();

    setMessage("Resume updated successfully!");
    setIsEditing(false);
  };

  const handleDownload = () => {
    window.print();
  };

  return (
    <div className="resume-page">

      {/* =========================
          PAGE HEADER
      ========================= */}

      <div className="resume-page-header no-print">
        <div>
          <p className="resume-label">
            STUDENT CAREER
          </p>

          <h1>My Resume</h1>

          <p className="resume-page-description">
            Create and manage your professional resume.
          </p>
        </div>

        <div className="resume-header-actions">
          {!isEditing && (
            <button
              className="resume-edit-button"
              onClick={handleEdit}
            >
              <Edit3 size={17} />
              Edit Resume
            </button>
          )}

          <button
            className="resume-download-button"
            onClick={handleDownload}
          >
            <Download size={17} />
            Download Resume
          </button>
        </div>
      </div>

      {message && (
        <div className="resume-success-message no-print">
          {message}
        </div>
      )}

      {/* =========================
          EDIT MODE
      ========================= */}

      {isEditing ? (
        <form
          className="resume-edit-container no-print"
          onSubmit={handleSave}
        >

          {/* Personal Information */}

          <section className="resume-edit-card">
            <div className="resume-edit-card-header">
              <div>
                <h2>Personal Information</h2>
                <p>
                  Add your basic contact information.
                </p>
              </div>

              <User size={22} />
            </div>

            <div className="resume-form-grid">

              <div className="resume-input-group">
                <label>Full Name</label>

                <input
                  type="text"
                  name="name"
                  value={resumeData.name}
                  onChange={handlePersonalChange}
                  required
                />
              </div>

              <div className="resume-input-group">
                <label>Professional Title</label>

                <input
                  type="text"
                  name="title"
                  value={resumeData.title}
                  onChange={handlePersonalChange}
                />
              </div>

              <div className="resume-input-group">
                <label>Email</label>

                <input
                  type="email"
                  name="email"
                  value={resumeData.email}
                  onChange={handlePersonalChange}
                  required
                />
              </div>

              <div className="resume-input-group">
                <label>Phone</label>

                <input
                  type="text"
                  name="phone"
                  value={resumeData.phone}
                  onChange={handlePersonalChange}
                />
              </div>

              <div className="resume-input-group">
                <label>Location</label>

                <input
                  type="text"
                  name="location"
                  value={resumeData.location}
                  onChange={handlePersonalChange}
                />
              </div>

              <div className="resume-input-group">
                <label>GitHub</label>

                <input
                  type="url"
                  name="github"
                  value={resumeData.github}
                  onChange={handlePersonalChange}
                />
              </div>

              <div className="resume-input-group">
                <label>LinkedIn</label>

                <input
                  type="url"
                  name="linkedin"
                  value={resumeData.linkedin}
                  onChange={handlePersonalChange}
                />
              </div>

              <div className="resume-input-group full-width">
                <label>Professional Summary</label>

                <textarea
                  value={resumeData.summary}
                  onChange={handleSummaryChange}
                  rows="6"
                />
              </div>

            </div>
          </section>

          {/* Education */}

          <section className="resume-edit-card">

            <div className="resume-edit-card-header">
              <div>
                <h2>Education</h2>
                <p>
                  Add your academic qualifications.
                </p>
              </div>

              <GraduationCap size={22} />
            </div>

            {resumeData.education.map((education) => (
              <div
                className="resume-repeatable-item"
                key={education.id}
              >

                <div className="repeatable-item-header">
                  <h3>Education</h3>

                  <button
                    type="button"
                    className="resume-delete-button"
                    onClick={() =>
                      deleteEducation(education.id)
                    }
                  >
                    <Trash2 size={16} />
                    Delete
                  </button>
                </div>

                <div className="resume-form-grid">

                  <div className="resume-input-group">
                    <label>Degree</label>

                    <input
                      type="text"
                      value={education.degree}
                      onChange={(e) =>
                        handleEducationChange(
                          education.id,
                          "degree",
                          e.target.value
                        )
                      }
                    />
                  </div>

                  <div className="resume-input-group">
                    <label>Institution</label>

                    <input
                      type="text"
                      value={education.institution}
                      onChange={(e) =>
                        handleEducationChange(
                          education.id,
                          "institution",
                          e.target.value
                        )
                      }
                    />
                  </div>

                  <div className="resume-input-group">
                    <label>Year</label>

                    <input
                      type="text"
                      value={education.year}
                      onChange={(e) =>
                        handleEducationChange(
                          education.id,
                          "year",
                          e.target.value
                        )
                      }
                    />
                  </div>

                  <div className="resume-input-group full-width">
                    <label>Description</label>

                    <textarea
                      rows="4"
                      value={education.description}
                      onChange={(e) =>
                        handleEducationChange(
                          education.id,
                          "description",
                          e.target.value
                        )
                      }
                    />
                  </div>

                </div>
              </div>
            ))}

            <button
              type="button"
              className="resume-add-button"
              onClick={addEducation}
            >
              <Plus size={16} />
              Add Education
            </button>

          </section>

          {/* Skills */}

          <section className="resume-edit-card">

            <div className="resume-edit-card-header">
              <div>
                <h2>Skills</h2>

                <p>
                  Add skills separated by commas.
                </p>
              </div>

              <Code2 size={22} />
            </div>

            <div className="resume-input-group">

              <label>Skills</label>

              <input
                type="text"
                value={resumeData.skills.join(", ")}
                onChange={handleSkillsChange}
                placeholder="React, JavaScript, Python, Django..."
              />

            </div>

          </section>

          {/* Projects */}

          <section className="resume-edit-card">

            <div className="resume-edit-card-header">
              <div>
                <h2>Projects</h2>

                <p>
                  Add your academic and personal projects.
                </p>
              </div>

              <BriefcaseBusiness size={22} />
            </div>

            {resumeData.projects.map((project) => (
              <div
                className="resume-repeatable-item"
                key={project.id}
              >

                <div className="repeatable-item-header">

                  <h3>Project</h3>

                  <button
                    type="button"
                    className="resume-delete-button"
                    onClick={() =>
                      deleteProject(project.id)
                    }
                  >
                    <Trash2 size={16} />
                    Delete
                  </button>

                </div>

                <div className="resume-form-grid">

                  <div className="resume-input-group">
                    <label>Project Name</label>

                    <input
                      type="text"
                      value={project.name}
                      onChange={(e) =>
                        handleProjectChange(
                          project.id,
                          "name",
                          e.target.value
                        )
                      }
                    />
                  </div>

                  <div className="resume-input-group">
                    <label>Technologies</label>

                    <input
                      type="text"
                      value={project.technologies}
                      onChange={(e) =>
                        handleProjectChange(
                          project.id,
                          "technologies",
                          e.target.value
                        )
                      }
                    />
                  </div>

                  <div className="resume-input-group full-width">
                    <label>Description</label>

                    <textarea
                      rows="4"
                      value={project.description}
                      onChange={(e) =>
                        handleProjectChange(
                          project.id,
                          "description",
                          e.target.value
                        )
                      }
                    />
                  </div>

                </div>
              </div>
            ))}

            <button
              type="button"
              className="resume-add-button"
              onClick={addProject}
            >
              <Plus size={16} />
              Add Project
            </button>

          </section>

          {/* Certifications */}

          <section className="resume-edit-card">

            <div className="resume-edit-card-header">
              <div>
                <h2>Certifications</h2>

                <p>
                  Add your professional certifications.
                </p>
              </div>

              <Award size={22} />
            </div>

            {resumeData.certifications.map(
              (certification) => (
                <div
                  className="resume-repeatable-item"
                  key={certification.id}
                >

                  <div className="repeatable-item-header">

                    <h3>Certification</h3>

                    <button
                      type="button"
                      className="resume-delete-button"
                      onClick={() =>
                        deleteCertification(
                          certification.id
                        )
                      }
                    >
                      <Trash2 size={16} />
                      Delete
                    </button>

                  </div>

                  <div className="resume-form-grid">

                    <div className="resume-input-group">
                      <label>Certification Name</label>

                      <input
                        type="text"
                        value={certification.name}
                        onChange={(e) =>
                          handleCertificationChange(
                            certification.id,
                            "name",
                            e.target.value
                          )
                        }
                      />
                    </div>

                    <div className="resume-input-group">
                      <label>Organization</label>

                      <input
                        type="text"
                        value={certification.organization}
                        onChange={(e) =>
                          handleCertificationChange(
                            certification.id,
                            "organization",
                            e.target.value
                          )
                        }
                      />
                    </div>

                    <div className="resume-input-group">
                      <label>Year</label>

                      <input
                        type="text"
                        value={certification.year}
                        onChange={(e) =>
                          handleCertificationChange(
                            certification.id,
                            "year",
                            e.target.value
                          )
                        }
                      />
                    </div>

                  </div>

                </div>
              )
            )}

            <button
              type="button"
              className="resume-add-button"
              onClick={addCertification}
            >
              <Plus size={16} />
              Add Certification
            </button>

          </section>

          {/* Edit Buttons */}

          <div className="resume-form-actions">

            <button
              type="button"
              className="resume-cancel-button"
              onClick={handleCancel}
            >
              <X size={17} />
              Cancel
            </button>

            <button
              type="submit"
              className="resume-save-button"
            >
              <Save size={17} />
              Save Resume
            </button>

          </div>

        </form>
      ) : (

        /* =========================
           RESUME PREVIEW
        ========================= */

        <div className="resume-document">

          {/* Resume Header */}

          <header className="resume-document-header">

            <div className="resume-name-section">

              <h1>{resumeData.name}</h1>

              <h2>{resumeData.title}</h2>

              <div className="resume-contact-row">

                <span>
                  <Mail size={14} />
                  {resumeData.email}
                </span>

                <span>
                  <Phone size={14} />
                  {resumeData.phone}
                </span>

                <span>
                  <MapPin size={14} />
                  {resumeData.location}
                </span>

              </div>

              <div className="resume-social-row">

                <a
                  href={resumeData.github}
                  target="_blank"
                  rel="noreferrer"
                >
                  <Github size={15} />
                  GitHub
                </a>

                <a
                  href={resumeData.linkedin}
                  target="_blank"
                  rel="noreferrer"
                >
                  <Linkedin size={15} />
                  LinkedIn
                </a>

              </div>

            </div>

          </header>

          {/* Summary */}

          <section className="resume-document-section">

            <div className="resume-section-heading">
              <User size={18} />
              <h2>Professional Summary</h2>
            </div>

            <p className="resume-summary">
              {resumeData.summary}
            </p>

          </section>

          {/* Education */}

          {resumeData.education.length > 0 && (
            <section className="resume-document-section">

              <div className="resume-section-heading">
                <GraduationCap size={18} />
                <h2>Education</h2>
              </div>

              {resumeData.education.map(
                (education) => (
                  <div
                    className="resume-document-item"
                    key={education.id}
                  >

                    <div className="resume-item-top">

                      <div>
                        <h3>
                          {education.degree}
                        </h3>

                        <p>
                          {education.institution}
                        </p>
                      </div>

                      <span>
                        {education.year}
                      </span>

                    </div>

                    {education.description && (
                      <p>
                        {education.description}
                      </p>
                    )}

                  </div>
                )
              )}

            </section>
          )}

          {/* Skills */}

          {resumeData.skills.length > 0 && (
            <section className="resume-document-section">

              <div className="resume-section-heading">
                <Code2 size={18} />
                <h2>Skills</h2>
              </div>

              <div className="resume-skills">

                {resumeData.skills.map(
                  (skill, index) => (
                    <span key={index}>
                      {skill}
                    </span>
                  )
                )}

              </div>

            </section>
          )}

          {/* Projects */}

          {resumeData.projects.length > 0 && (
            <section className="resume-document-section">

              <div className="resume-section-heading">
                <BriefcaseBusiness size={18} />
                <h2>Projects</h2>
              </div>

              {resumeData.projects.map(
                (project) => (
                  <div
                    className="resume-document-item"
                    key={project.id}
                  >

                    <h3>{project.name}</h3>

                    <p>
                      {project.description}
                    </p>

                    {project.technologies && (
                      <p className="resume-technologies">
                        <strong>
                          Technologies:
                        </strong>{" "}
                        {project.technologies}
                      </p>
                    )}

                  </div>
                )
              )}

            </section>
          )}

          {/* Certifications */}

          {resumeData.certifications.length > 0 && (
            <section className="resume-document-section">

              <div className="resume-section-heading">
                <Award size={18} />
                <h2>Certifications</h2>
              </div>

              {resumeData.certifications.map(
                (certification) => (
                  <div
                    className="resume-document-item"
                    key={certification.id}
                  >

                    <div className="resume-item-top">

                      <div>

                        <h3>
                          {certification.name}
                        </h3>

                        <p>
                          {certification.organization}
                        </p>

                      </div>

                      <span>
                        {certification.year}
                      </span>

                    </div>

                  </div>
                )
              )}

            </section>
          )}

          <footer className="resume-footer">
            <p>
              Resume generated from Student Career Profile
            </p>
          </footer>

        </div>
      )}

    </div>
  );
}

export default Resume;