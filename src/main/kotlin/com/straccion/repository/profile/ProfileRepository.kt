package com.straccion.repository.profile

import com.straccion.model.ProfileResponse
import com.straccion.model.UpdateUserParams
import com.straccion.util.Response

interface ProfileRepository {

    suspend fun getUserById(userId: Long, currentUserId: Long): Response<ProfileResponse>

    suspend fun updateUser(updateUserParams: UpdateUserParams): Response<ProfileResponse>
}