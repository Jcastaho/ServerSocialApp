package com.straccion.repository.user

import com.straccion.model.AuthResponse
import com.straccion.model.SignInParams
import com.straccion.model.SignUpParams
import com.straccion.util.Response

interface UserRepository {
    suspend fun signUp(params: SignUpParams): Response<AuthResponse>
    suspend fun signIn(params: SignInParams): Response<AuthResponse>
}