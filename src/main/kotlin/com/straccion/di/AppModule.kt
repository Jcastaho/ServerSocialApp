package com.straccion.di

import com.straccion.dao.user.UserDao
import com.straccion.dao.user.UserDaoImpl
import com.straccion.repository.user.UserRepository
import com.straccion.repository.user.UserRepositoryImpl
import org.koin.dsl.module

val appModule = module{
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<UserDao> { UserDaoImpl() }
}