package com.straccion.di

import com.straccion.dao.follows.FollowsDao
import com.straccion.dao.follows.FollowsDaoImpl
import com.straccion.dao.post.PostDao
import com.straccion.dao.post.PostDaoImpl
import com.straccion.dao.post_comments.PostCommentsDao
import com.straccion.dao.post_comments.PostCommentsDaoImpl
import com.straccion.dao.post_likes.PostLikesDao
import com.straccion.dao.post_likes.PostLikesDaoImpl
import com.straccion.dao.user.UserDao
import com.straccion.dao.user.UserDaoImpl
import com.straccion.repository.auth.AuthRepository
import com.straccion.repository.auth.AuthRepositoryImpl
import com.straccion.repository.follows.FollowsRepository
import com.straccion.repository.follows.FollowsRepositoryImpl
import com.straccion.repository.post.PostRepository
import com.straccion.repository.post.PostRepositoryImpl
import com.straccion.repository.post_comments.PostCommentsRepository
import com.straccion.repository.post_comments.PostCommentsRepositoryImpl
import com.straccion.repository.post_likes.PostLikesRepository
import com.straccion.repository.post_likes.PostLikesRepositoryImpl
import com.straccion.repository.profile.ProfileRepository
import com.straccion.repository.profile.ProfileRepositoryImpl
import org.koin.dsl.module

val appModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<UserDao> { UserDaoImpl() }
    single<FollowsDao> { FollowsDaoImpl() }
    single<FollowsRepository> { FollowsRepositoryImpl(get(), get()) }
    single<PostLikesDao> { PostLikesDaoImpl() }
    single<PostDao> { PostDaoImpl()}
    single<PostRepository> { PostRepositoryImpl(get(), get(), get()) }
    single<ProfileRepository> { ProfileRepositoryImpl(get(), get()) }
    single<PostCommentsDao> { PostCommentsDaoImpl() }
    single<PostCommentsRepository> { PostCommentsRepositoryImpl(get(), get()) }
    single<PostLikesRepository> { PostLikesRepositoryImpl(get(), get()) }
}