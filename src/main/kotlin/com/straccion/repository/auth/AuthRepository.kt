package com.straccion.repository.auth

import com.straccion.model.AuthResponse
import com.straccion.model.SignInParams
import com.straccion.model.SignUpParams
import com.straccion.util.Response

interface AuthRepository {
    suspend fun signUp(params: SignUpParams): Response<AuthResponse>
    suspend fun signIn(params: SignInParams): Response<AuthResponse>
}