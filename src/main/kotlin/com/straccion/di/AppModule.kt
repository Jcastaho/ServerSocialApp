package com.straccion.di

import com.straccion.dao.user.UserDao
import com.straccion.dao.user.UserDaoImpl
import com.straccion.repository.auth.AuthRepository
import com.straccion.repository.auth.AuthRepositoryImpl
import org.koin.dsl.module

val appModule = module{
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<UserDao> { UserDaoImpl() }
}