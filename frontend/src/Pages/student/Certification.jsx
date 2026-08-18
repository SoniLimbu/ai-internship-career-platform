import { useState } from "react";
import {
  Award,
  Calendar,
  Building2,
  ExternalLink,
  Edit3,
  Trash2,
  Plus,
  X,
  Save,
} from "lucide-react";
import "./Certification.css";

function Certification() {
  const [certifications, setCertifications] = useState([
    {
      id: 1,
      name: "Google UX Design Certificate",
      organization: "Google",
      issueDate: "2026-08-01",
      expirationDate: "",
      credentialId: "GOOGLE-UX-2026",
      credentialUrl: "https://www.coursera.org/",
      description:
        "Completed professional training in UX design, user research, wireframing and prototyping.",
    },
  ]);

  const emptyForm = {
    name: "",
    organization: "",
    issueDate: "",
    expirationDate: "",
    credentialId: "",
    credentialUrl: "",
    description: "",
  };

  const [formData, setFormData] = useState(emptyForm);
  const [isFormOpen, setIsFormOpen] = useState(false);
  const [editingId, setEditingId] = useState(null);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleAdd = () => {
    setFormData(emptyForm);
    setEditingId(null);
    setIsFormOpen(true);
  };

  const handleEdit = (certification) => {
    setFormData({
      name: certification.name,
      organization: certification.organization,
      issueDate: certification.issueDate,
      expirationDate: certification.expirationDate,
      credentialId: certification.credentialId,
      credentialUrl: certification.credentialUrl,
      description: certification.description,
    });

    setEditingId(certification.id);
    setIsFormOpen(true);
  };

  const handleDelete = (id) => {
    const confirmed = window.confirm(
      "Are you sure you want to delete this certification?"
    );

    if (confirmed) {
      setCertifications(
        certifications.filter((certification) => certification.id !== id)
      );
    }
  };

  const handleCancel = () => {
    setFormData(emptyForm);
    setEditingId(null);
    setIsFormOpen(false);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (editingId) {
      setCertifications(
        certifications.map((certification) =>
          certification.id === editingId
            ? {
                ...certification,
                ...formData,
              }
            : certification
        )
      );
    } else {
      const newCertification = {
        id: Date.now(),
        ...formData,
      };

      setCertifications([
        ...certifications,
        newCertification,
      ]);
    }

    setFormData(emptyForm);
    setEditingId(null);
    setIsFormOpen(false);
  };

  const formatDate = (date) => {
    if (!date) return "No expiration";

    return new Date(date).toLocaleDateString("en-US", {
      year: "numeric",
      month: "long",
    });
  };

  return (
    <div className="certification-page">

      {/* Header */}
      <div className="certification-header">
        <div>
          <p className="certification-label">
            STUDENT CAREER
          </p>

          <h1>Certifications</h1>

          <p className="certification-description">
            Add and manage your professional certifications and credentials.
          </p>
        </div>

        {!isFormOpen && (
          <button
            className="add-certification-button"
            onClick={handleAdd}
          >
            <Plus size={18} />
            Add Certification
          </button>
        )}
      </div>

      {/* Add / Edit Form */}
      {isFormOpen && (
        <form
          className="certification-form-card"
          onSubmit={handleSubmit}
        >
          <div className="form-header">
            <div>
              <h2>
                {editingId
                  ? "Edit Certification"
                  : "Add Certification"}
              </h2>

              <p>
                Add your professional certificate or achievement.
              </p>
            </div>

            <button
              type="button"
              className="close-form-button"
              onClick={handleCancel}
            >
              <X size={20} />
            </button>
          </div>

          <div className="certification-form-grid">

            {/* Certification Name */}
            <div className="form-group">
              <label>
                Certification Name
              </label>

              <input
                type="text"
                name="name"
                value={formData.name}
                onChange={handleChange}
                placeholder="e.g. Google UX Design Certificate"
                required
              />
            </div>

            {/* Organization */}
            <div className="form-group">
              <label>
                Issuing Organization
              </label>

              <input
                type="text"
                name="organization"
                value={formData.organization}
                onChange={handleChange}
                placeholder="e.g. Google"
                required
              />
            </div>

            {/* Issue Date */}
            <div className="form-group">
              <label>
                Issue Date
              </label>

              <input
                type="date"
                name="issueDate"
                value={formData.issueDate}
                onChange={handleChange}
                required
              />
            </div>

            {/* Expiration */}
            <div className="form-group">
              <label>
                Expiration Date
              </label>

              <input
                type="date"
                name="expirationDate"
                value={formData.expirationDate}
                onChange={handleChange}
              />

              <small>
                Leave empty if the certification does not expire.
              </small>
            </div>

            {/* Credential ID */}
            <div className="form-group">
              <label>
                Credential ID
              </label>

              <input
                type="text"
                name="credentialId"
                value={formData.credentialId}
                onChange={handleChange}
                placeholder="e.g. ABC123456"
              />
            </div>

            {/* Credential URL */}
            <div className="form-group">
              <label>
                Credential URL
              </label>

              <input
                type="url"
                name="credentialUrl"
                value={formData.credentialUrl}
                onChange={handleChange}
                placeholder="https://example.com/certificate"
              />
            </div>

            {/* Description */}
            <div className="form-group full-width">
              <label>
                Description
              </label>

              <textarea
                name="description"
                value={formData.description}
                onChange={handleChange}
                rows="5"
                placeholder="Describe what you learned or achieved..."
              />
            </div>

          </div>

          {/* Form Buttons */}
          <div className="form-actions">

            <button
              type="button"
              className="cancel-button"
              onClick={handleCancel}
            >
              <X size={17} />
              Cancel
            </button>

            <button
              type="submit"
              className="save-button"
            >
              <Save size={17} />

              {editingId
                ? "Save Changes"
                : "Add Certification"}
            </button>

          </div>
        </form>
      )}

      {/* Certifications List */}
      <div className="certification-content">

        <div className="section-title">
          <div>
            <h2>My Certifications</h2>

            <p>
              {certifications.length} certification
              {certifications.length !== 1 ? "s" : ""}
            </p>
          </div>
        </div>

        {certifications.length === 0 ? (
          <div className="empty-certifications">

            <div className="empty-icon">
              <Award size={34} />
            </div>

            <h3>No certifications yet</h3>

            <p>
              Add your professional certifications to strengthen
              your career profile.
            </p>

            <button
              className="add-certification-button"
              onClick={handleAdd}
            >
              <Plus size={18} />
              Add Your First Certification
            </button>

          </div>
        ) : (
          <div className="certification-list">

            {certifications.map((certification) => (
              <div
                className="certification-card"
                key={certification.id}
              >

                {/* Card Icon */}
                <div className="certification-icon">
                  <Award size={26} />
                </div>

                {/* Card Content */}
                <div className="certification-info">

                  <h3>
                    {certification.name}
                  </h3>

                  <div className="organization">
                    <Building2 size={16} />

                    <span>
                      {certification.organization}
                    </span>
                  </div>

                  <div className="certification-meta">

                    <div>
                      <Calendar size={15} />

                      <span>
                        Issued:{" "}
                        {formatDate(
                          certification.issueDate
                        )}
                      </span>
                    </div>

                    <div>
                      <Calendar size={15} />

                      <span>
                        {certification.expirationDate
                          ? `Expires: ${formatDate(
                              certification.expirationDate
                            )}`
                          : "No expiration"}
                      </span>
                    </div>

                  </div>

                  {certification.credentialId && (
                    <p className="credential-id">
                      <strong>
                        Credential ID:
                      </strong>{" "}
                      {certification.credentialId}
                    </p>
                  )}

                  {certification.description && (
                    <p className="certification-description-text">
                      {certification.description}
                    </p>
                  )}

                  <div className="certification-actions">

                    {certification.credentialUrl && (
                      <a
                        href={certification.credentialUrl}
                        target="_blank"
                        rel="noreferrer"
                        className="credential-link"
                      >
                        <ExternalLink size={15} />
                        View Credential
                      </a>
                    )}

                    <button
                      className="edit-button"
                      onClick={() =>
                        handleEdit(certification)
                      }
                    >
                      <Edit3 size={15} />
                      Edit
                    </button>

                    <button
                      className="delete-button"
                      onClick={() =>
                        handleDelete(certification.id)
                      }
                    >
                      <Trash2 size={15} />
                      Delete
                    </button>

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

export default Certification;