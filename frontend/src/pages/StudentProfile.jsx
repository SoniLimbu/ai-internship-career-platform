import { useState } from "react";
import API from "../api";

function StudentProfile() {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    phone: "",
    bio: "",
    education: "",
    skills: "",
    github: "",
    linkedin: "",
  });

  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await API.post("/students/profiles/", formData);

      setMessage("Profile created successfully! 🎉");

      setFormData({
        name: "",
        email: "",
        phone: "",
        bio: "",
        education: "",
        skills: "",
        github: "",
        linkedin: "",
      });
    } catch (error) {
      console.error(error);
      setMessage("Something went wrong. Please check the fields.");
    }
  };

  return (
    <div style={{ maxWidth: "600px", margin: "40px auto" }}>
      <h1>Create Student Profile</h1>

      {message && <p>{message}</p>}

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="name"
          placeholder="Full Name"
          value={formData.name}
          onChange={handleChange}
          required
        />

        <br />
        <br />

        <input
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleChange}
          required
        />

        <br />
        <br />

        <input
          type="text"
          name="phone"
          placeholder="Phone"
          value={formData.phone}
          onChange={handleChange}
        />

        <br />
        <br />

        <textarea
          name="bio"
          placeholder="Tell us about yourself"
          value={formData.bio}
          onChange={handleChange}
        />

        <br />
        <br />

        <input
          type="text"
          name="education"
          placeholder="Education"
          value={formData.education}
          onChange={handleChange}
        />

        <br />
        <br />

        <input
          type="text"
          name="skills"
          placeholder="Skills (e.g. React, Python, Django)"
          value={formData.skills}
          onChange={handleChange}
        />

        <br />
        <br />

        <input
          type="url"
          name="github"
          placeholder="GitHub URL"
          value={formData.github}
          onChange={handleChange}
        />

        <br />
        <br />

        <input
          type="url"
          name="linkedin"
          placeholder="LinkedIn URL"
          value={formData.linkedin}
          onChange={handleChange}
        />

        <br />
        <br />

        <button type="submit">
          Create Profile
        </button>
      </form>
    </div>
  );
}

export default StudentProfile;