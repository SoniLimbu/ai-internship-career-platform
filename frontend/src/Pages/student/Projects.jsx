import { useState } from "react";
import {
  FolderKanban,
  Code2,
  ExternalLink,
  Edit3,
  Trash2,
  Plus,
  X,
  Save,
  Calendar,
} from "lucide-react";
import "./Projects.css";

function Projects() {
  const [projects, setProjects] = useState([
    {
      id: 1,
      title: "AI Internship Career Platform",
      description:
        "An AI-powered platform that helps students find internships and improve their career opportunities.",
      technologies: ["React", "Django", "PostgreSQL", "AI"],
      github: "https://github.com/",
      liveDemo: "",
      startDate: "2026-01",
      endDate: "2026-05",
    },
    {
      id: 2,
      title: "Flower Shop Website",
      description:
        "A modern responsive flower shop website where users can explore flowers and place orders.",
      technologies: ["React", "CSS", "JavaScript"],
      github: "https://github.com/",
      liveDemo: "",
      startDate: "2025-10",
      endDate: "2025-12",
    },
  ]);

  const [showForm, setShowForm] = useState(false);
  const [editingId, setEditingId] = useState(null);

  const [formData, setFormData] = useState({
    title: "",
    description: "",
    technologies: "",
    github: "",
    liveDemo: "",
    startDate: "",
    endDate: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleAddProject = () => {
    setEditingId(null);

    setFormData({
      title: "",
      description: "",
      technologies: "",
      github: "",
      liveDemo: "",
      startDate: "",
      endDate: "",
    });

    setShowForm(true);
  };

  const handleEditProject = (project) => {
    setEditingId(project.id);

    setFormData({
      title: project.title,
      description: project.description,
      technologies: project.technologies.join(", "),
      github: project.github,
      liveDemo: project.liveDemo,
      startDate: project.startDate,
      endDate: project.endDate,
    });

    setShowForm(true);
  };

  const handleDeleteProject = (id) => {
    const confirmDelete = window.confirm(
      "Are you sure you want to delete this project?"
    );

    if (confirmDelete) {
      setProjects(projects.filter((project) => project.id !== id));
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!formData.title.trim()) {
      alert("Please enter the project title.");
      return;
    }

    if (!formData.description.trim()) {
      alert("Please enter the project description.");
      return;
    }

    const technologies = formData.technologies
      .split(",")
      .map((tech) => tech.trim())
      .filter((tech) => tech !== "");

    if (editingId) {
      setProjects(
        projects.map((project) =>
          project.id === editingId
            ? {
                ...project,
                title: formData.title,
                description: formData.description,
                technologies,
                github: formData.github,
                liveDemo: formData.liveDemo,
                startDate: formData.startDate,
                endDate: formData.endDate,
              }
            : project
        )
      );
    } else {
      const newProject = {
        id: Date.now(),
        title: formData.title,
        description: formData.description,
        technologies,
        github: formData.github,
        liveDemo: formData.liveDemo,
        startDate: formData.startDate,
        endDate: formData.endDate,
      };

      setProjects([...projects, newProject]);
    }

    setShowForm(false);
    setEditingId(null);

    setFormData({
      title: "",
      description: "",
      technologies: "",
      github: "",
      liveDemo: "",
      startDate: "",
      endDate: "",
    });
  };

  const handleCancel = () => {
    setShowForm(false);
    setEditingId(null);

    setFormData({
      title: "",
      description: "",
      technologies: "",
      github: "",
      liveDemo: "",
      startDate: "",
      endDate: "",
    });
  };

  return (
    <div className="projects-page">
      {/* Header */}
      <div className="projects-header">
        <div>
          <h1>My Projects</h1>
          <p>
            Showcase your projects, technical skills, and practical experience.
          </p>
        </div>

        <button className="add-project-btn" onClick={handleAddProject}>
          <Plus size={18} />
          Add Project
        </button>
      </div>

      {/* Summary */}
      <div className="projects-summary">
        <div className="summary-card">
          <div className="summary-icon">
            <FolderKanban size={22} />
          </div>

          <div>
            <h3>{projects.length}</h3>
            <p>Total Projects</p>
          </div>
        </div>

        <div className="summary-card">
          <div className="summary-icon">
            <Code2 size={22} />
          </div>

          <div>
            <h3>
              {
                new Set(
                  projects.flatMap((project) => project.technologies)
                ).size
              }
            </h3>
            <p>Technologies Used</p>
          </div>
        </div>
      </div>

      {/* Add/Edit Form */}
      {showForm && (
        <div className="project-form-card">
          <div className="form-header">
            <div>
              <h2>{editingId ? "Edit Project" : "Add New Project"}</h2>
              <p>
                {editingId
                  ? "Update your project information."
                  : "Add a project to your portfolio."}
              </p>
            </div>

            <button className="close-btn" onClick={handleCancel}>
              <X size={20} />
            </button>
          </div>

          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label>Project Title *</label>

              <input
                type="text"
                name="title"
                value={formData.title}
                onChange={handleChange}
                placeholder="e.g. AI Internship Career Platform"
              />
            </div>

            <div className="form-group">
              <label>Description *</label>

              <textarea
                name="description"
                value={formData.description}
                onChange={handleChange}
                placeholder="Describe your project..."
                rows="5"
              />
            </div>

            <div className="form-group">
              <label>Technologies</label>

              <input
                type="text"
                name="technologies"
                value={formData.technologies}
                onChange={handleChange}
                placeholder="React, JavaScript, Django, PostgreSQL"
              />

              <small>
                Separate technologies using commas.
              </small>
            </div>

            <div className="form-row">
              <div className="form-group">
                <label>Start Date</label>

                <input
                  type="month"
                  name="startDate"
                  value={formData.startDate}
                  onChange={handleChange}
                />
              </div>

              <div className="form-group">
                <label>End Date</label>

                <input
                  type="month"
                  name="endDate"
                  value={formData.endDate}
                  onChange={handleChange}
                />
              </div>
            </div>

            <div className="form-group">
              <label>GitHub URL</label>

              <div className="input-with-icon">
                <Code2 size={18} />

                <input
                  type="url"
                  name="github"
                  value={formData.github}
                  onChange={handleChange}
                  placeholder="https://github.com/username/project"
                />
              </div>
            </div>

            <div className="form-group">
              <label>Live Demo URL</label>

              <div className="input-with-icon">
                <ExternalLink size={18} />

                <input
                  type="url"
                  name="liveDemo"
                  value={formData.liveDemo}
                  onChange={handleChange}
                  placeholder="https://your-project.com"
                />
              </div>
            </div>

            <div className="form-actions">
              <button
                type="button"
                className="cancel-btn"
                onClick={handleCancel}
              >
                <X size={17} />
                Cancel
              </button>

              <button type="submit" className="save-btn">
                <Save size={17} />
                {editingId ? "Update Project" : "Save Project"}
              </button>
            </div>
          </form>
        </div>
      )}

      {/* Projects List */}
      <div className="projects-section">
        <div className="section-title">
          <h2>Your Projects</h2>

          <span>
            {projects.length}{" "}
            {projects.length === 1 ? "project" : "projects"}
          </span>
        </div>

        {projects.length === 0 ? (
          <div className="empty-projects">
            <div className="empty-icon">
              <FolderKanban size={40} />
            </div>

            <h3>No projects yet</h3>

            <p>
              Start building your portfolio by adding your first project.
            </p>

            <button
              className="add-project-btn"
              onClick={handleAddProject}
            >
              <Plus size={18} />
              Add Your First Project
            </button>
          </div>
        ) : (
          <div className="projects-grid">
            {projects.map((project) => (
              <div className="project-card" key={project.id}>
                {/* Card Header */}
                <div className="project-card-header">
                  <div className="project-icon">
                    <FolderKanban size={22} />
                  </div>

                  <div className="project-actions">
                    <button
                      className="icon-btn edit"
                      onClick={() => handleEditProject(project)}
                      title="Edit project"
                    >
                      <Edit3 size={17} />
                    </button>

                    <button
                      className="icon-btn delete"
                      onClick={() => handleDeleteProject(project.id)}
                      title="Delete project"
                    >
                      <Trash2 size={17} />
                    </button>
                  </div>
                </div>

                {/* Project Content */}
                <div className="project-content">
                  <h3>{project.title}</h3>

                  <p className="project-description">
                    {project.description}
                  </p>

                  {/* Technologies */}
                  <div className="technology-section">
                    <h4>
                      <Code2 size={16} />
                      Technologies
                    </h4>

                    <div className="technology-list">
                      {project.technologies.map((technology, index) => (
                        <span key={index} className="technology-tag">
                          {technology}
                        </span>
                      ))}
                    </div>
                  </div>

                  {/* Date */}
                  {(project.startDate || project.endDate) && (
                    <div className="project-date">
                      <Calendar size={16} />

                      <span>
                        {project.startDate || "N/A"}
                        {" - "}
                        {project.endDate || "Present"}
                      </span>
                    </div>
                  )}

                  {/* Links */}
                  <div className="project-links">
                    {project.github && (
                      <a
                        href={project.github}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="project-link github-link"
                      >
                        <Code2 size={17} />
                        GitHub
                      </a>
                    )}

                    {project.liveDemo && (
                      <a
                        href={project.liveDemo}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="project-link demo-link"
                      >
                        <ExternalLink size={17} />
                        Live Demo
                      </a>
                    )}
                  </div>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

export default Projects;