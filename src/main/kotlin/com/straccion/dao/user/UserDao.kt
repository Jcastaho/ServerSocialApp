package com.straccion.dao.user

import com.straccion.model.SignUpParams
import com.straccion.model.User

interface UserDao {
    suspend fun insert(params: SignUpParams): User?
    suspend fun findByEmail(email: String): User?
}