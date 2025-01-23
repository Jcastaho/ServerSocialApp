package com.straccion.dao.user

import com.straccion.model.SignUpParams

interface UserDao {
    suspend fun insert(params: SignUpParams): UserRow?
    suspend fun findByEmail(email: String): UserRow?
}