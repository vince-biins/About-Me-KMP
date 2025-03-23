package com.project.home.data


import com.project.home.data.model.BackgroundDto
import com.project.home.data.model.BasicProfileDto
import com.project.home.data.model.ContactDto
import com.project.home.data.model.DetailedProfileDto
import com.project.home.data.model.ExperienceDto
import com.project.home.data.model.ExpertiseDto
import com.project.home.data.model.SkillDto
import com.project.home.domain.enums.BackgroundType

internal object Mock {
    val basicProfile = BasicProfileDto(
        headerTitle = "I’m a Mobile Developer",
        headerSubtitle = "Turning Ideas into Mobile Solutions.",
        headerImageUrl = ""
    )

    val detailedProfile = DetailedProfileDto(
        title = "I’m Vincent Ola-ola, a Mobile Application Developer",
        description = "I specialized in Kotlin KMP, Kotlin Native, and Flutter. I love creating high-performance, user-friendly apps with clean architecture and smooth interactions. Beyond coding, I enjoy drawing, gaming, and watching movie series—activities that fuel my creativity, problem-solving, and appreciation for great storytelling. Whether I’m building intuitive mobile apps or diving into immersive worlds, I’m always looking for ways to innovate and grow.",
        name = "Vincent Ola-ola",
        age = 24,
        location = "Binangonan, Rizal, Philippines",
        email = "olaolavincent22@gmail.com"
    )

    val background = listOf<BackgroundDto>(
        BackgroundDto(
            headerTitle = "Professional Journey",
            experience = listOf<ExperienceDto>(
                ExperienceDto(
                    imageUrl = "",
                    title = "HC Consumer Finance Philippines, Inc.",
                    date = "October 2023 - Present",
                    description = "Worked as a Mobile App Developer using Kotlin and Flutter.",
                    type = BackgroundType.PROFESSIONAL
                )
            )
        ),
        BackgroundDto(
            headerTitle = "On-the-Job Training",
            experience = listOf<ExperienceDto>(
                ExperienceDto(
                    imageUrl = "",
                    title = "Node Innovators Web Development Services",
                    date = "SY: 2022-2023",
                    description = "Worked as a Mobile Android App Developer intern using  Kotlin.",
                    type = BackgroundType.OJT
                )
            )
        ),

        BackgroundDto(
            headerTitle = "Education",
            experience = listOf<ExperienceDto>(
                ExperienceDto(
                    imageUrl = "",
                    title = "Graduated in College with a Degree of Bachelor of Science in Computer Engineering",
                    date = "June 2023",
                    description = "Graduated at University of Rizal System - Morong Campus",
                    type = BackgroundType.EDUCATION
                ),
                ExperienceDto(
                    imageUrl = "",
                    title = "Graduated Senior High School of Information and Communication Technology",
                    date = "April 2019",
                    description = "Graduated at Angono National High School",
                    type = BackgroundType.EDUCATION
                ),
            )
        ),
        BackgroundDto(
            headerTitle = "Certifications & Trainings",
            experience = listOf<ExperienceDto>(
                ExperienceDto(
                    imageUrl = "",
                    title = "National Certificate II (TESDA) for Computer Systems Servicing",
                    date = "January 2019",
                    description = "Passed this assessment during my Senior High School years.",
                    type = BackgroundType.TRAININGS_CERTIFICATE
                ),
                ExperienceDto(
                    imageUrl = "",
                    title = "Lean Six Sigma White Belt Certification",
                    date = "December 2024",
                    description = "Passed this assessment during my working years in HCPH.",
                    type = BackgroundType.TRAININGS_CERTIFICATE
                ),
            )
        ),
    )


    val expertise = listOf<ExpertiseDto>(
        ExpertiseDto(
            title = "Mobile Development",
            imageUrl = "",
            skill = listOf<SkillDto>(
                SkillDto(
                    title = "Kotlin",
                    subTitle = listOf<String>(
                        "Kotlin Multiplatform (KMP)",
                        "Jetpack Compose",
                    )
                ),
                SkillDto(
                    title = "Flutter",
                    subTitle = emptyList()
                ),
            )
        ),

        ExpertiseDto(
            title = "Backend Development",
            imageUrl = "",
            skill = listOf<SkillDto>(
                SkillDto(
                    title = "Ktor",
                    subTitle = emptyList()
                ),
                SkillDto(
                    title = "Java",
                    subTitle = emptyList()
                ),
                SkillDto(
                    title = "RESTful API",
                    subTitle = emptyList()
                ),
                SkillDto(
                    title = "SQLite, MySQL",
                    subTitle = emptyList()
                ),
            )
        ),

        ExpertiseDto(
            title = "Dev Tools & Workflow",
            imageUrl = "",
            skill = listOf<SkillDto>(
                SkillDto(
                    title = "Git,  GitHub, GitLab",
                    subTitle = emptyList()
                ),
                SkillDto(
                    title = "Agile & Scrum Methodologies",
                    subTitle = emptyList()
                ),
            )
        ),

        ExpertiseDto(
            title = "Additional Familiarity",
            imageUrl = "",
            skill = listOf<SkillDto>(
                SkillDto(
                    title = "HTML, CSS, PHP",
                    subTitle = emptyList()
                ),
                SkillDto(
                    title = "C++",
                    subTitle = emptyList()
                ),
                SkillDto(
                    title = "C#",
                    subTitle = emptyList()
                ),
                SkillDto(
                    title = "Arduino",
                    subTitle = emptyList()
                ),
            )
        ),
    )


    val contactDtos = listOf(
        ContactDto(
            title = "Gmail",
            url = "olaolavincent22@gmail.com"
        ),
        ContactDto(
            title = "Github",
            url = "https://github.com/vince-biins"
        ),
        ContactDto(
            title = "LinkedIn",
            url = "www.linkedin.com/in/vincent-ola-ola-8a711a282"
        ),
        ContactDto(
            title = "Facebook",
            url = "https://www.facebook.com/vincent.razekyut"
        ),
    )
}