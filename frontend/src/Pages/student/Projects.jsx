```jsx
import { useState } from "react";
import {
  Plus,
  Edit3,
  Trash2,
  X,
  Save,
  ExternalLink,
  Code2,
  FolderGit2,
} from "lucide-react";

import "./Projects.css";

function Projects() {
  const [isEditing, setIsEditing] = useState(false);
  const [editingId, setEditingId] = useState(null);
  const [message, setMessage] = useState("");

  const [projects, setProjects] = useState([
    {
      id: 1,
      name: "AI Internship & Career Platform",
      description:
        "An AI-powered platform that helps university students discover internships, manage their career profiles, and improve their career development.",
      technologies: "React, Django, PostgreSQL",
      github:
        "https://github.com/SoniLimbu/ai-internship-career-platform",
      live: "",
    },
    {
      id: 2,
      name: "Weather App",
      description:
        "A responsive weather application that displays current weather information using a weather API.",
      technologies: "HTML, CSS, JavaScript, Weather API",
      github: "https://github.com/SoniLimbu/weather-app",
      live: "https://sonilimbu.github.io/weather-app/",
    },
  ]);

  const [formData, setFormData] = useState({
    name: "",
    description: "",
    technologies: "",
    github: "",
    live: "",
  });

  // Handle input changes
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  // Add project
  const handleAddProject = () => {
    setFormData({
      name: "",
      description: "",
      technologies: "",
      github: "",
      live: "",
    });

    setEditingId(null);
    setMessage("");
    setIsEditing(true);
  };

  // Edit project
  const handleEditProject = (project) => {
    setFormData({
      name: project.name,
      description: project.description,
      technologies: project.technologies,
      github: project.github,
      live: project.live,
    });

    setEditingId(project.id);
    setMessage("");
    setIsEditing(true);
  };

  // Cancel form
  const handleCancel = () => {
    setIsEditing(false);
    setEditingId(null);
    setMessage("");
  };

  // Submit project
  const handleSubmit = (e) => {
    e.preventDefault();

    if (editingId) {
      setProjects(
        projects.map((project) =>
          project.id === editingId
            ? {
                ...project,
                ...formData,
              }
            : project
        )
      );

      setMessage("Project updated successfully!");
    } else {
      const newProject = {
        id: Date.now(),
        ...formData,
      };

      setProjects([...projects, newProject]);

      setMessage("Project added successfully!");
    }

    setIsEditing(false);
    setEditingId(null);
  };

  // Delete project
  const handleDelete = (id) => {
    const confirmDelete = window.confirm(
      "Are you sure you want to delete this project?"
    );

    if (!confirmDelete) {
      return;
    }

    setProjects(
      projects.filter((project) => project.id !== id)
    );

    setMessage("Project deleted successfully!");
  };

  return (
    <div className="projects-page">

      {/* =========================
          PAGE HEADER
      ========================= */}

      <div className="projects-header">
        <div>
          <p className="projects-label">STUDENT PROJECTS</p>

          <h1>My Projects</h1>

          <p className="projects-description">
            Showcase your academic, personal, and professional
            projects.
          </p>
        </div>

        {!isEditing && (
          <button
            className="add-project-button"
            onClick={handleAddProject}
          >
            <Plus size={18} />
            Add Project
          </button>
        )}
      </div>

      {/* =========================
          SUCCESS MESSAGE
      ========================= */}

      {message && (
        <div className="projects-success-message">
          {message}
        </div>
      )}

      {/* =========================
          ADD / EDIT FORM
      ========================= */}

      {isEditing ? (
        <form
          className="project-form-card"
          onSubmit={handleSubmit}
        >

          {/* FORM HEADER */}

          <div className="project-form-header">
            <div>
              <h2>
                {editingId
                  ? "Edit Project"
                  : "Add New Project"}
              </h2>

              <p>
                Add details about your project.
              </p>
            </div>

            <button
              type="button"
              className="close-project-form"
              onClick={handleCancel}
            >
              <X size={20} />
            </button>
          </div>

          {/* FORM GRID */}

          <div className="project-form-grid">

            {/* PROJECT NAME */}

            <div className="project-input-group full-width">
              <label>Project Name</label>

              <input
                type="text"
                name="name"
                value={formData.name}
                onChange={handleChange}
                placeholder="e.g. AI Internship Platform"
                required
              />
            </div>

            {/* DESCRIPTION */}

            <div className="project-input-group full-width">
              <label>Project Description</label>

              <textarea
                name="description"
                rows="5"
                value={formData.description}
                onChange={handleChange}
                placeholder="Describe what your project does..."
                required
              />
            </div>

            {/* TECHNOLOGIES */}

            <div className="project-input-group full-width">
              <label>Technologies Used</label>

              <input
                type="text"
                name="technologies"
                value={formData.technologies}
                onChange={handleChange}
                placeholder="React, JavaScript, Django, PostgreSQL"
                required
              />
            </div>

            {/* GITHUB */}

            <div className="project-input-group">
              <label>GitHub URL</label>

              <input
                type="url"
                name="github"
                value={formData.github}
                onChange={handleChange}
                placeholder="https://github.com/..."
              />
            </div>

            {/* LIVE URL */}

            <div className="project-input-group">
              <label>Live Project URL</label>

              <input
                type="url"
                name="live"
                value={formData.live}
                onChange={handleChange}
                placeholder="https://..."
              />
            </div>
          </div>

          {/* FORM BUTTONS */}

          <div className="project-form-actions">

            <button
              type="button"
              className="cancel-project-button"
              onClick={handleCancel}
            >
              <X size={17} />
              Cancel
            </button>

            <button
              type="submit"
              className="save-project-button"
            >
              <Save size={17} />

              {editingId
                ? "Save Changes"
                : "Add Project"}
            </button>

          </div>
        </form>
      ) : (

        /* =========================
           PROJECT LIST
        ========================= */

        <div className="projects-content">

          {projects.length === 0 ? (

            /* EMPTY STATE */

            <div className="projects-empty">

              <div className="projects-empty-icon">
                <FolderGit2 size={38} />
              </div>

              <h2>No Projects Yet</h2>

              <p>
                Start building your portfolio by adding
                your first project.
              </p>

              <button
                className="add-project-button"
                onClick={handleAddProject}
              >
                <Plus size={18} />
                Add Your First Project
              </button>

            </div>

          ) : (

            /* PROJECT CARDS */

            <div className="projects-grid">

              {projects.map((project) => (

                <div
                  className="project-card"
                  key={project.id}
                >

                  {/* CARD TOP */}

                  <div className="project-card-top">

                    <div className="project-icon">
                      <Code2 size={24} />
                    </div>

                    <div className="project-card-actions">

                      {/* EDIT */}

                      <button
                        type="button"
                        className="project-icon-button"
                        onClick={() =>
                          handleEditProject(project)
                        }
                        title="Edit project"
                      >
                        <Edit3 size={17} />
                      </button>

                      {/* DELETE */}

                      <button
                        type="button"
                        className="project-icon-button delete"
                        onClick={() =>
                          handleDelete(project.id)
                        }
                        title="Delete project"
                      >
                        <Trash2 size={17} />
                      </button>

                    </div>
                  </div>

                  {/* PROJECT NAME */}

                  <h2>{project.name}</h2>

                  {/* DESCRIPTION */}

                  <p className="project-description">
                    {project.description}
                  </p>

                  {/* TECHNOLOGIES */}

                  <div className="project-technologies">

                    {project.technologies
                      .split(",")
                      .map((technology, index) => (
                        <span key={index}>
                          {technology.trim()}
                        </span>
                      ))}

                  </div>

                  {/* PROJECT LINKS */}

                  <div className="project-links">

                    {/* GITHUB */}

                    {project.github && (
                      <a
                        href={project.github}
                        target="_blank"
                        rel="noreferrer"
                      >
                        <Code2 size={17} />
                        GitHub
                      </a>
                    )}

                    {/* LIVE PROJECT */}

                    {project.live && (
                      <a
                        href={project.live}
                        target="_blank"
                        rel="noreferrer"
                      >
                        <ExternalLink size={17} />
                        Live Demo
                      </a>
                    )}

                  </div>

                </div>

              ))}

            </div>

          )}

        </div>

      )}

    </div>
  );
}

export default Projects;
```
