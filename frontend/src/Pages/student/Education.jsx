import { useState } from "react";
import {
  GraduationCap,
  Plus,
  Pencil,
  Trash2,
  X,
  Save,
  CalendarDays,
  BookOpen,
} from "lucide-react";

import "./Education.css";

function Education() {
  const [isAdding, setIsAdding] = useState(false);
  const [editingId, setEditingId] = useState(null);
  const [message, setMessage] = useState("");

  const [educationList, setEducationList] = useState([
    {
      id: 1,
      institution: "Itahari International College",
      degree: "BSc (Hons) Computing",
      startDate: "2023-09",
      endDate: "2027-06",
      description:
        "Studying computing with a focus on software development, web technologies, databases and artificial intelligence.",
    },
  ]);

  const emptyForm = {
    institution: "",
    degree: "",
    startDate: "",
    endDate: "",
    description: "",
  };

  const [formData, setFormData] = useState(emptyForm);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleAddClick = () => {
    setFormData(emptyForm);
    setEditingId(null);
    setMessage("");
    setIsAdding(true);
  };

  const handleEdit = (education) => {
    setFormData({
      institution: education.institution,
      degree: education.degree,
      startDate: education.startDate,
      endDate: education.endDate,
      description: education.description,
    });

    setEditingId(education.id);
    setMessage("");
    setIsAdding(true);
  };

  const handleCancel = () => {
    setFormData(emptyForm);
    setEditingId(null);
    setIsAdding(false);
    setMessage("");
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (editingId) {
      setEducationList((currentList) =>
        currentList.map((education) =>
          education.id === editingId
            ? {
                ...education,
                ...formData,
              }
            : education
        )
      );

      setMessage("Education updated successfully!");
    } else {
      const newEducation = {
        id: Date.now(),
        ...formData,
      };

      setEducationList((currentList) => [
        ...currentList,
        newEducation,
      ]);

      setMessage("Education added successfully!");
    }

    setFormData(emptyForm);
    setEditingId(null);
    setIsAdding(false);
  };

  const handleDelete = (id) => {
    const confirmed = window.confirm(
      "Are you sure you want to delete this education record?"
    );

    if (!confirmed) {
      return;
    }

    setEducationList((currentList) =>
      currentList.filter(
        (education) => education.id !== id
      )
    );

    setMessage("Education deleted successfully!");
  };

  return (
    <div className="education-page">

      {/* =====================================
          HEADER
      ====================================== */}

      <div className="education-header">

        <div>
          <p className="education-label">
            EDUCATION
          </p>

          <h1>
            My Education
          </h1>

          <p className="education-description">
            Manage your academic background and educational
            qualifications.
          </p>
        </div>

        {!isAdding && (
          <button
            className="add-education-button"
            onClick={handleAddClick}
          >
            <Plus size={18} />
            Add Education
          </button>
        )}

      </div>


      {/* =====================================
          MESSAGE
      ====================================== */}

      {message && (
        <div className="education-success-message">
          {message}
        </div>
      )}


      {/* =====================================
          ADD / EDIT FORM
      ====================================== */}

      {isAdding && (
        <form
          className="education-form-card"
          onSubmit={handleSubmit}
        >

          <div className="education-form-header">

            <div>
              <h2>
                {editingId
                  ? "Edit Education"
                  : "Add Education"}
              </h2>

              <p>
                Add details about your academic background.
              </p>
            </div>

            <button
              type="button"
              className="close-education-button"
              onClick={handleCancel}
            >
              <X size={18} />
            </button>

          </div>


          <div className="education-form-grid">

            {/* Institution */}

            <div className="education-input-group">

              <label>
                Institution / University
              </label>

              <input
                type="text"
                name="institution"
                value={formData.institution}
                onChange={handleChange}
                placeholder="e.g. Itahari International College"
                required
              />

            </div>


            {/* Degree */}

            <div className="education-input-group">

              <label>
                Degree / Program
              </label>

              <input
                type="text"
                name="degree"
                value={formData.degree}
                onChange={handleChange}
                placeholder="e.g. BSc (Hons) Computing"
                required
              />

            </div>


            {/* Start Date */}

            <div className="education-input-group">

              <label>
                Start Date
              </label>

              <input
                type="month"
                name="startDate"
                value={formData.startDate}
                onChange={handleChange}
                required
              />

            </div>


            {/* End Date */}

            <div className="education-input-group">

              <label>
                End Date
              </label>

              <input
                type="month"
                name="endDate"
                value={formData.endDate}
                onChange={handleChange}
              />

            </div>


            {/* Description */}

            <div className="education-input-group full-width">

              <label>
                Description
              </label>

              <textarea
                name="description"
                rows="5"
                value={formData.description}
                onChange={handleChange}
                placeholder="Describe your course, major subjects, achievements or other relevant information..."
              />

            </div>

          </div>


          <div className="education-form-actions">

            <button
              type="button"
              className="cancel-education-button"
              onClick={handleCancel}
            >
              <X size={17} />
              Cancel
            </button>

            <button
              type="submit"
              className="save-education-button"
            >
              <Save size={17} />
              {editingId
                ? "Save Changes"
                : "Add Education"}
            </button>

          </div>

        </form>
      )}


      {/* =====================================
          EDUCATION LIST
      ====================================== */}

      <section className="education-list-card">

        <div className="education-list-header">

          <div>
            <h2>
              Education History
            </h2>

            <p>
              Your academic qualifications and background.
            </p>
          </div>

          <div className="education-count">
            {educationList.length}{" "}
            {educationList.length === 1
              ? "Record"
              : "Records"}
          </div>

        </div>


        {educationList.length === 0 ? (

          /* EMPTY STATE */

          <div className="education-empty">

            <div className="education-empty-icon">
              <GraduationCap size={27} />
            </div>

            <h3>
              No education added yet
            </h3>

            <p>
              Add your university, degree and other
              academic information to complete your profile.
            </p>

            <button
              className="empty-education-button"
              onClick={handleAddClick}
            >
              <Plus size={17} />
              Add Education
            </button>

          </div>

        ) : (

          /* EDUCATION ITEMS */

          <div className="education-items">

            {educationList.map((education) => (

              <article
                className="education-item"
                key={education.id}
              >

                <div className="education-item-icon">
                  <GraduationCap size={23} />
                </div>


                <div className="education-item-content">

                  <div className="education-item-top">

                    <div>

                      <h3>
                        {education.degree}
                      </h3>

                      <p className="education-institution">
                        {education.institution}
                      </p>

                    </div>

                    <div className="education-actions">

                      <button
                        type="button"
                        onClick={() =>
                          handleEdit(education)
                        }
                        aria-label="Edit education"
                      >
                        <Pencil size={16} />
                      </button>

                      <button
                        type="button"
                        onClick={() =>
                          handleDelete(education.id)
                        }
                        aria-label="Delete education"
                      >
                        <Trash2 size={16} />
                      </button>

                    </div>

                  </div>


                  <div className="education-date">

                    <CalendarDays size={15} />

                    <span>
                      {education.startDate || "Not specified"}
                      {" — "}
                      {education.endDate || "Present"}
                    </span>

                  </div>


                  {education.description && (
                    <p className="education-item-description">
                      {education.description}
                    </p>
                  )}


                  <div className="education-tag">
                    <BookOpen size={13} />
                    Academic Education
                  </div>

                </div>

              </article>

            ))}

          </div>

        )}

      </section>

    </div>
  );
}

export default Education;