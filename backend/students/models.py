
from django.contrib.auth.models import User
from django.db import models


class StudentProfile(models.Model):

    CAREER_CHOICES = [
        ("frontend", "Frontend Development"),
        ("backend", "Backend Development"),
        ("fullstack", "Full Stack Development"),
        ("qa", "QA / Software Testing"),
        ("data", "Data Science"),
        ("ai", "Artificial Intelligence"),
        ("uiux", "UI/UX Design"),
        ("cybersecurity", "Cybersecurity"),
        ("other", "Other"),
    ]

    user = models.OneToOneField(
        User,
        on_delete=models.CASCADE,
        related_name="student_profile"
    )

    profile_picture = models.ImageField(
        upload_to="profile_pictures/",
        blank=True,
        null=True
    )

    university = models.CharField(max_length=200)

    degree = models.CharField(max_length=200)

    academic_year = models.CharField(max_length=50)

    graduation_year = models.PositiveIntegerField(
        blank=True,
        null=True
    )

    location = models.CharField(max_length=200)

    career_interest = models.CharField(
        max_length=50,
        choices=CAREER_CHOICES
    )

    bio = models.TextField(
        blank=True
    )

    phone = models.CharField(
        max_length=20,
        blank=True
    )

    created_at = models.DateTimeField(
        auto_now_add=True
    )

    updated_at = models.DateTimeField(
        auto_now=True
    )

    def __str__(self):
        return self.user.username