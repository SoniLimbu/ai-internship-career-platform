from django.contrib import admin
from .models import StudentProfile


@admin.register(StudentProfile)
class StudentProfileAdmin(admin.ModelAdmin):
    list_display = (
        "user",
        "university",
        "degree",
        "career_interest",
        "graduation_year",
    )

    search_fields = (
        "user__username",
        "user__email",
        "university",
        "degree",
    )

    list_filter = (
        "career_interest",
        "graduation_year",
    )