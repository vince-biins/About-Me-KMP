package com.project.home.data.mappers

import com.project.home.data.model.BackgroundDto
import com.project.home.data.model.BasicProfileDto
import com.project.home.data.model.ContactDto
import com.project.home.data.model.DetailedProfileDto
import com.project.home.data.model.ExperienceDto
import com.project.home.data.model.ExpertiseDto
import com.project.home.data.model.ProjectDto
import com.project.home.data.model.SkillDto
import com.project.home.data.model.TechSkillDto
import com.project.home.domain.model.Background
import com.project.home.domain.model.BasicProfile
import com.project.home.domain.model.Contact
import com.project.home.domain.model.DetailedProfile
import com.project.home.domain.model.Experience
import com.project.home.domain.model.Expertise
import com.project.home.domain.model.Project
import com.project.home.domain.model.Skill
import com.project.home.domain.model.TechSkill
import com.project.utils.DateHelper

fun BasicProfileDto.transform() = BasicProfile(
    headerTitle = headerTitle,
    headerSubtitle = headerSubtitle,
    headerImageUrl = headerImageUrl,
    headerSuperTItle = headerSuperTitle

)

fun DetailedProfileDto.transform() = DetailedProfile(
    title = title,
    description = description,
    name = name,
    age = with(DateHelper) {
        val date = parseDate(birthday)
        date?.let {
            calculateAge(date)
        } ?: 0
    },
    location = location,
    email = email,
    fileUrl = fileUrl,
)

fun BackgroundDto.transform() = Background(
    headerTitle = headerTitle,
    experience = experience.map { it.transform() },
)

fun ExperienceDto.transform() = Experience(
    imageUrl = imageUrl,
    title = title,
    date = date,
    description = description,
    type = type,
)

fun ContactDto.transform() = Contact(
    title = title,
    url = url,
    imageUrl = imageUrl
)

fun ExpertiseDto.transform() = Expertise(
    title = title,
    skill = skill.map { it.transform() },
    imageUrl = imageUrl ?: "",
)

fun SkillDto.transform() = Skill(
    title = title,
    subTitle = subSkills?.map { it.title } ?: emptyList(),
)

fun TechSkillDto.transform() = TechSkill (
    title = title,
    imageUrl = imageUrl,
)

fun ProjectDto.transform() = Project(
    title = title,
    description = description,
    appUrl = url.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
    stack = stack.split(','),
)
