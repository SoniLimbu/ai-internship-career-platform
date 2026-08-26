import { useState } from "react";
import {
  User,
  Mail,
  Phone,
  MapPin,
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
  Link,
} from "lucide-react";

import "./Resume.css";

function Resume() {
  const [isEditing, setIsEditing] = useState(false);
  const [message, setMessage] = useState("");

  const [resumeData, setResumeData] = useState({
    name: "Soni Limbu",
    email: "soni@example.com",
    phone: "98XXXXXXXX",
    location: "Itahari, Nepal",

    title: "BSc (Hons) Computing Student",

    summary:
      "Computing student interested in frontend development, UI/UX design, and building useful digital products.",

    education: [
      {
        id: 1,
        degree: "BSc (Hons) Computing",
        institution: "Itahari International College",
        year: "2024 - Present",
      },
    ],

    skills: [
      "React",
      "JavaScript",
      "HTML",
      "CSS",
      "Python",
      "Django",
    ],

    certifications: [
      {
        id: 1,
        name: "Web Development",
        issuer: "Online Certification",
        year: "2026",
      },
    ],

    projects: [
      {
        id: 1,
        name: "AI Internship & Career Platform",
        description:
          "An AI-powered platform designed to help university students find internships and develop their careers.",
        technologies: "React, Django, PostgreSQL",
      },
    ],

    github: "https://github.com/SoniLimbu",
    linkedin: "https://linkedin.com/",
  });

  const [newSkill, setNewSkill] = useState("");

  const handleChange = (e) => {
    setResumeData({
      ...resumeData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSave = (e) => {
    e.preventDefault();

    setMessage("Resume updated successfully!");
    setIsEditing(false);
  };

  const handleCancel = () => {
    setMessage("");
    setIsEditing(false);
  };

  const addSkill = () => {
    const skill = newSkill.trim();

    if (!skill) return;

    if (resumeData.skills.includes(skill)) {
      setNewSkill("");
      return;
    }

    setResumeData({
      ...resumeData,
      skills: [...resumeData.skills, skill],
    });

    setNewSkill("");
  };

  const removeSkill = (skillToRemove) => {
    setResumeData({
      ...resumeData,
      skills: resumeData.skills.filter(
        (skill) => skill !== skillToRemove
      ),
    });
  };

  const addEducation = () => {
    const newEducation = {
      id: Date.now(),
      degree: "New Qualification",
      institution: "Institution Name",
      year: "Year",
    };

    setResumeData({
      ...resumeData,
      education: [...resumeData.education, newEducation],
    });
  };

  const removeEducation = (id) => {
    setResumeData({
      ...resumeData,
      education: resumeData.education.filter(
        (education) => education.id !== id
      ),
    });
  };

  const updateEducation = (id, field, value) => {
    setResumeData({
      ...resumeData,
      education: resumeData.education.map((education) =>
        education.id === id
          ? {
              ...education,
              [field]: value,
            }
          : education
      ),
    });
  };

  const addCertification = () => {
    const newCertification = {
      id: Date.now(),
      name: "New Certification",
      issuer: "Organization",
      year: "2026",
    };

    setResumeData({
      ...resumeData,
      certifications: [
        ...resumeData.certifications,
        newCertification,
      ],
    });
  };

  const removeCertification = (id) => {
    setResumeData({
      ...resumeData,
      certifications: resumeData.certifications.filter(
        (certification) => certification.id !== id
      ),
    });
  };

  const updateCertification = (id, field, value) => {
    setResumeData({
      ...resumeData,
      certifications: resumeData.certifications.map(
        (certification) =>
          certification.id === id
            ? {
                ...certification,
                [field]: value,
              }
            : certification
      ),
    });
  };

  const addProject = () => {
    const newProject = {
      id: Date.now(),
      name: "New Project",
      description: "Project description",
      technologies: "Technologies used",
    };

    setResumeData({
      ...resumeData,
      projects: [...resumeData.projects, newProject],
    });
  };

  const removeProject = (id) => {
    setResumeData({
      ...resumeData,
      projects: resumeData.projects.filter(
        (project) => project.id !== id
      ),
    });
  };

  const updateProject = (id, field, value) => {
    setResumeData({
      ...resumeData,
      projects: resumeData.projects.map((project) =>
        project.id === id
          ? {
              ...project,
              [field]: value,
            }
          : project
      ),
    });
  };

  const handleDownload = () => {
    window.print();
  };

  return (
    <div className="resume-page">

      {/* HEADER */}
      <div className="resume-header">
        <div>
          <p className="resume-label">CAREER PROFILE</p>

          <h1>My Resume</h1>

          <p className="resume-description">
            Build and manage your professional resume.
          </p>
        </div>

        <div className="resume-header-actions">
          <button
            className="download-resume-button"
            onClick={handleDownload}
          >
            <Download size={17} />
            Download
          </button>

          {!isEditing && (
            <button
              className="edit-resume-button"
              onClick={() => {
                setMessage("");
                setIsEditing(true);
              }}
            >
              <Edit3 size={17} />
              Edit Resume
            </button>
          )}
        </div>
      </div>

      {message && (
        <div className="resume-success-message">
          {message}
        </div>
      )}

      {isEditing ? (

        /* =========================
           EDIT MODE
        ========================= */

        <form
          className="resume-edit-container"
          onSubmit={handleSave}
        >

          {/* PERSONAL INFORMATION */}
          <div className="resume-edit-card">
            <div className="resume-card-title">
              <User size={20} />

              <div>
                <h2>Personal Information</h2>

                <p>
                  Update your basic contact information.
                </p>
              </div>
            </div>

            <div className="resume-form-grid">

              <div className="resume-input-group">
                <label>Full Name</label>

                <input
                  type="text"
                  name="name"
                  value={resumeData.name}
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="resume-input-group">
                <label>Professional Title</label>

                <input
                  type="text"
                  name="title"
                  value={resumeData.title}
                  onChange={handleChange}
                />
              </div>

              <div className="resume-input-group">
                <label>Email</label>

                <input
                  type="email"
                  name="email"
                  value={resumeData.email}
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="resume-input-group">
                <label>Phone</label>

                <input
                  type="text"
                  name="phone"
                  value={resumeData.phone}
                  onChange={handleChange}
                />
              </div>

              <div className="resume-input-group full-width">
                <label>Location</label>

                <input
                  type="text"
                  name="location"
                  value={resumeData.location}
                  onChange={handleChange}
                />
              </div>

              <div className="resume-input-group full-width">
                <label>Professional Summary</label>

                <textarea
                  name="summary"
                  rows="5"
                  value={resumeData.summary}
                  onChange={handleChange}
                />
              </div>

              <div className="resume-input-group">
                <label>GitHub</label>

                <input
                  type="url"
                  name="github"
                  value={resumeData.github}
                  onChange={handleChange}
                />
              </div>

              <div className="resume-input-group">
                <label>LinkedIn</label>

                <input
                  type="url"
                  name="linkedin"
                  value={resumeData.linkedin}
                  onChange={handleChange}
                />
              </div>

            </div>
          </div>


          {/* EDUCATION */}
          <div className="resume-edit-card">

            <div className="resume-section-heading">
              <div className="resume-card-title">
                <GraduationCap size={20} />

                <div>
                  <h2>Education</h2>
                  <p>Add your educational background.</p>
                </div>
              </div>

              <button
                type="button"
                className="add-resume-button"
                onClick={addEducation}
              >
                <Plus size={16} />
                Add
              </button>
            </div>

            {resumeData.education.map((education) => (
              <div
                className="resume-array-item"
                key={education.id}
              >

                <div className="resume-array-grid">

                  <div className="resume-input-group">
                    <label>Degree</label>

                    <input
                      value={education.degree}
                      onChange={(e) =>
                        updateEducation(
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
                      value={education.institution}
                      onChange={(e) =>
                        updateEducation(
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
                      value={education.year}
                      onChange={(e) =>
                        updateEducation(
                          education.id,
                          "year",
                          e.target.value
                        )
                      }
                    />
                  </div>

                </div>

                <button
                  type="button"
                  className="delete-resume-button"
                  onClick={() =>
                    removeEducation(education.id)
                  }
                >
                  <Trash2 size={17} />
                </button>

              </div>
            ))}

          </div>


          {/* SKILLS */}
          <div className="resume-edit-card">

            <div className="resume-card-title">
              <Code2 size={20} />

              <div>
                <h2>Skills</h2>

                <p>
                  Add the skills you want employers to see.
                </p>
              </div>
            </div>

            <div className="skill-add-row">

              <input
                type="text"
                placeholder="Enter a skill"
                value={newSkill}
                onChange={(e) =>
                  setNewSkill(e.target.value)
                }
                onKeyDown={(e) => {
                  if (e.key === "Enter") {
                    e.preventDefault();
                    addSkill();
                  }
                }}
              />

              <button
                type="button"
                className="add-resume-button"
                onClick={addSkill}
              >
                <Plus size={16} />
                Add Skill
              </button>

            </div>

            <div className="resume-skills-list">

              {resumeData.skills.map((skill) => (
                <div
                  className="resume-skill-tag"
                  key={skill}
                >
                  {skill}

                  <button
                    type="button"
                    onClick={() =>
                      removeSkill(skill)
                    }
                  >
                    <X size={14} />
                  </button>
                </div>
              ))}

            </div>

          </div>


          {/* CERTIFICATIONS */}
          <div className="resume-edit-card">

            <div className="resume-section-heading">

              <div className="resume-card-title">
                <Award size={20} />

                <div>
                  <h2>Certifications</h2>

                  <p>
                    Add certificates and achievements.
                  </p>
                </div>
              </div>

              <button
                type="button"
                className="add-resume-button"
                onClick={addCertification}
              >
                <Plus size={16} />
                Add
              </button>

            </div>

            {resumeData.certifications.map(
              (certification) => (
                <div
                  className="resume-array-item"
                  key={certification.id}
                >

                  <div className="resume-array-grid">

                    <div className="resume-input-group">
                      <label>Certificate</label>

                      <input
                        value={certification.name}
                        onChange={(e) =>
                          updateCertification(
                            certification.id,
                            "name",
                            e.target.value
                          )
                        }
                      />
                    </div>

                    <div className="resume-input-group">
                      <label>Issuer</label>

                      <input
                        value={certification.issuer}
                        onChange={(e) =>
                          updateCertification(
                            certification.id,
                            "issuer",
                            e.target.value
                          )
                        }
                      />
                    </div>

                    <div className="resume-input-group">
                      <label>Year</label>

                      <input
                        value={certification.year}
                        onChange={(e) =>
                          updateCertification(
                            certification.id,
                            "year",
                            e.target.value
                          )
                        }
                      />
                    </div>

                  </div>

                  <button
                    type="button"
                    className="delete-resume-button"
                    onClick={() =>
                      removeCertification(
                        certification.id
                      )
                    }
                  >
                    <Trash2 size={17} />
                  </button>

                </div>
              )
            )}

          </div>


          {/* PROJECTS */}
          <div className="resume-edit-card">

            <div className="resume-section-heading">

              <div className="resume-card-title">
                <BriefcaseBusiness size={20} />

                <div>
                  <h2>Projects</h2>

                  <p>
                    Showcase your important projects.
                  </p>
                </div>
              </div>

              <button
                type="button"
                className="add-resume-button"
                onClick={addProject}
              >
                <Plus size={16} />
                Add
              </button>

            </div>

            {resumeData.projects.map((project) => (
              <div
                className="resume-project-edit"
                key={project.id}
              >

                <div className="resume-input-group">
                  <label>Project Name</label>

                  <input
                    value={project.name}
                    onChange={(e) =>
                      updateProject(
                        project.id,
                        "name",
                        e.target.value
                      )
                    }
                  />
                </div>

                <div className="resume-input-group">
                  <label>Description</label>

                  <textarea
                    rows="4"
                    value={project.description}
                    onChange={(e) =>
                      updateProject(
                        project.id,
                        "description",
                        e.target.value
                      )
                    }
                  />
                </div>

                <div className="resume-input-group">
                  <label>Technologies</label>

                  <input
                    value={project.technologies}
                    onChange={(e) =>
                      updateProject(
                        project.id,
                        "technologies",
                        e.target.value
                      )
                    }
                  />
                </div>

                <button
                  type="button"
                  className="delete-resume-button"
                  onClick={() =>
                    removeProject(project.id)
                  }
                >
                  <Trash2 size={17} />
                  Remove Project
                </button>

              </div>
            ))}

          </div>


          {/* FORM ACTIONS */}
          <div className="resume-form-actions">

            <button
              type="button"
              className="cancel-resume-button"
              onClick={handleCancel}
            >
              <X size={17} />
              Cancel
            </button>

            <button
              type="submit"
              className="save-resume-button"
            >
              <Save size={17} />
              Save Resume
            </button>

          </div>

        </form>

      ) : (

        /* =========================
           VIEW MODE
        ========================= */

        <div className="resume-document">

          {/* RESUME TOP */}
          <div className="resume-document-header">

            <div className="resume-avatar">
              SL
            </div>

            <div className="resume-person-info">

              <h2>{resumeData.name}</h2>

              <h3>{resumeData.title}</h3>

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

              <div className="resume-links">

                <a
                  href={resumeData.github}
                  target="_blank"
                  rel="noreferrer"
                >
                  <Link size={14} />
                  GitHub
                </a>

                <a
                  href={resumeData.linkedin}
                  target="_blank"
                  rel="noreferrer"
                >
                  <Link size={14} />
                  LinkedIn
                </a>

              </div>

            </div>

          </div>


          {/* SUMMARY */}
          <section className="resume-document-section">

            <h3>Professional Summary</h3>

            <p>{resumeData.summary}</p>

          </section>


          {/* EDUCATION */}
          <section className="resume-document-section">

            <h3>Education</h3>

            {resumeData.education.map((education) => (
              <div
                className="resume-document-item"
                key={education.id}
              >

                <div>
                  <strong>
                    {education.degree}
                  </strong>

                  <p>
                    {education.institution}
                  </p>
                </div>

                <span>
                  {education.year}
                </span>

              </div>
            ))}

          </section>


          {/* SKILLS */}
          <section className="resume-document-section">

            <h3>Skills</h3>

            <div className="resume-document-skills">

              {resumeData.skills.map((skill) => (
                <span key={skill}>
                  {skill}
                </span>
              ))}

            </div>

          </section>


          {/* CERTIFICATIONS */}
          <section className="resume-document-section">

            <h3>Certifications</h3>

            {resumeData.certifications.map(
              (certification) => (
                <div
                  className="resume-document-item"
                  key={certification.id}
                >

                  <div>
                    <strong>
                      {certification.name}
                    </strong>

                    <p>
                      {certification.issuer}
                    </p>
                  </div>

                  <span>
                    {certification.year}
                  </span>

                </div>
              )
            )}

          </section>


          {/* PROJECTS */}
          <section className="resume-document-section">

            <h3>Projects</h3>

            {resumeData.projects.map((project) => (
              <div
                className="resume-project-item"
                key={project.id}
              >

                <h4>{project.name}</h4>

                <p>
                  {project.description}
                </p>

                <span>
                  {project.technologies}
                </span>

              </div>
            ))}

          </section>

        </div>
      )}

    </div>
  );
}

export default Resume;