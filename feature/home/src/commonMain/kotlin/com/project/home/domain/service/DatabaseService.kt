package com.project.home.domain.service

import com.project.home.data.model.BackgroundDto
import com.project.home.data.model.BasicProfileDto
import com.project.home.data.model.ContactDto
import com.project.home.data.model.DetailedProfileDto
import com.project.home.data.model.ExpertiseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.KSerializer

interface DatabaseService {
    fun getContact(path: String): Flow<List<ContactDto>>
    fun getIntroProfile(path: String): Flow<BasicProfileDto?>
    fun getDetailProfile(path: String): Flow<DetailedProfileDto?>
    fun getExpertise(path: String): Flow<List<ExpertiseDto>>
    fun getExperience(path: String): Flow<List<BackgroundDto>>
}