import { useState } from "react";
import { Plus, Edit3, Trash2, Code2, Heart, X, Save } from "lucide-react";
import "./Skills.css";

function Skills() {
  const [skills, setSkills] = useState([
    { id: 1, name: "React", type: "Technical", level: "Advanced" },
    { id: 2, name: "JavaScript", type: "Technical", level: "Intermediate" },
    { id: 3, name: "Communication", type: "Soft Skill", level: "Advanced" },
  ]);

  const [showForm, setShowForm] = useState(false);
  const [editingId, setEditingId] = useState(null);
  const [formData, setFormData] = useState({
    name: "",
    type: "Technical",
    level: "Beginner",
  });
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const resetForm = () => {
    setFormData({ name: "", type: "Technical", level: "Beginner" });
    setEditingId(null);
    setShowForm(false);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!formData.name.trim()) {
      return;
    }

    if (editingId) {
      setSkills(
        skills.map((skill) =>
          skill.id === editingId ? { ...skill, ...formData } : skill
        )
      );
      setMessage("Skill updated successfully!");
    } else {
      const newSkill = { id: Date.now(), ...formData };
      setSkills([...skills, newSkill]);
      setMessage("Skill added successfully!");
    }

    resetForm();
    setTimeout(() => setMessage(""), 3000);
  };

  const handleEdit = (skill) => {
    setFormData({
      name: skill.name,
      type: skill.type,
      level: skill.level,
    });

    setEditingId(skill.id);
    setShowForm(true);
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  const handleDelete = (id) => {
    const confirmDelete = window.confirm(
      "Are you sure you want to delete this skill?"
    );

    if (!confirmDelete) {
      return;
    }

    setSkills(skills.filter((skill) => skill.id !== id));
    setMessage("Skill deleted successfully!");
    setTimeout(() => setMessage(""), 3000);
  };

  const technicalCount = skills.filter((skill) => skill.type === "Technical").length;
  const softSkillCount = skills.filter((skill) => skill.type === "Soft Skill").length;

  return (
    <div className="skills-page">
      <div className="skills-header">
        <div>
          <p className="skills-label">CAREER PROFILE</p>
          <h1>My Skills</h1>
          <p className="skills-description">
            Add and manage the skills you want to showcase to potential
            employers.
          </p>
        </div>

        {!showForm && (
          <button className="add-skill-button" onClick={() => setShowForm(true)}>
            <Plus size={18} />
            Add Skill
          </button>
        )}
      </div>

      {message && <div className="skills-success-message">{message}</div>}

      {showForm && (
        <form className="skills-form-card" onSubmit={handleSubmit}>
          <div className="skills-form-header">
            <div>
              <h2>{editingId ? "Edit Skill" : "Add New Skill"}</h2>
              <p>Add information about your skill and proficiency level.</p>
            </div>

            <button
              type="button"
              className="close-form-button"
              onClick={resetForm}
            >
              <X size={20} />
            </button>
          </div>

          <div className="skills-form-grid">
            <div className="skills-input-group">
              <label>Skill Name</label>
              <input
                type="text"
                name="name"
                value={formData.name}
                onChange={handleChange}
                placeholder="e.g. React, Python, Communication"
                required
              />
            </div>

            <div className="skills-input-group">
              <label>Skill Type</label>
              <select name="type" value={formData.type} onChange={handleChange}>
                <option value="Technical">Technical</option>
                <option value="Soft Skill">Soft Skill</option>
              </select>
            </div>

            <div className="skills-input-group">
              <label>Proficiency Level</label>
              <select name="level" value={formData.level} onChange={handleChange}>
                <option value="Beginner">Beginner</option>
                <option value="Intermediate">Intermediate</option>
                <option value="Advanced">Advanced</option>
              </select>
            </div>
          </div>

          <div className="skills-form-actions">
            <button
              type="button"
              className="cancel-skill-button"
              onClick={resetForm}
            >
              <X size={17} />
              Cancel
            </button>

            <button type="submit" className="save-skill-button">
              <Save size={17} />
              {editingId ? "Update Skill" : "Save Skill"}
            </button>
          </div>
        </form>
      )}

      <div className="skills-summary">
        <div className="skill-summary-card">
          <div className="summary-icon">
            <Code2 size={22} />
          </div>
          <div>
            <strong>{technicalCount}</strong>
            <span>Technical Skills</span>
          </div>
        </div>

        <div className="skill-summary-card">
          <div className="summary-icon">
            <Heart size={22} />
          </div>
          <div>
            <strong>{softSkillCount}</strong>
            <span>Soft Skills</span>
          </div>
        </div>

        <div className="skill-summary-card">
          <div className="summary-icon">
            <Code2 size={22} />
          </div>
          <div>
            <strong>{skills.length}</strong>
            <span>Total Skills</span>
          </div>
        </div>
      </div>

      <div className="skills-list-card">
        <div className="skills-list-header">
          <div>
            <h2>Your Skills</h2>
            <p>Skills displayed on your career profile.</p>
          </div>
        </div>

        {skills.length === 0 ? (
          <div className="skills-empty">
            <div className="empty-icon">
              <Code2 size={28} />
            </div>

            <h3>No skills added yet</h3>
            <p>Add your first skill to start building your career profile.</p>

            <button
              onClick={() => setShowForm(true)}
              className="empty-add-button"
            >
              <Plus size={17} />
              Add Your First Skill
            </button>
          </div>
        ) : (
          <div className="skills-list">
            {skills.map((skill) => (
              <div className="skill-item" key={skill.id}>
                <div className="skill-icon">
                  {skill.type === "Technical" ? (
                    <Code2 size={21} />
                  ) : (
                    <Heart size={21} />
                  )}
                </div>

                <div className="skill-information">
                  <h3>{skill.name}</h3>
                  <div className="skill-meta">
                    <span>{skill.type}</span>
                    <span>{skill.level}</span>
                  </div>
                </div>

                <div className="skill-actions">
                  <button onClick={() => handleEdit(skill)} title="Edit skill">
                    <Edit3 size={17} />
                  </button>
                  <button
                    onClick={() => handleDelete(skill.id)}
                    title="Delete skill"
                  >
                    <Trash2 size={17} />
                  </button>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

export default Skills;