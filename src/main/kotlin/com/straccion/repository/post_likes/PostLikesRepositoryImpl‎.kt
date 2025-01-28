package com.straccion.repository.post_likes

import com.straccion.dao.post.PostDao
import com.straccion.dao.post_likes.PostLikesDao
import com.straccion.model.LikeParams
import com.straccion.model.LikeResponse
import com.straccion.util.Response
import io.ktor.http.*

class PostLikesRepositoryImpl(
    private val likesDao: PostLikesDao,
    private val postDao: PostDao
) : PostLikesRepository {
    override suspend fun addLike(params: LikeParams): Response<LikeResponse> {
        val likeExists = likesDao.isPostLikedByUser(postId = params.postId, userId = params.userId)
        return if (likeExists){
            Response.Error(
                code = HttpStatusCode.Forbidden,
                data = LikeResponse(success = false, message = "Post already liked")
            )
        }else{
            val postLiked = likesDao.addLike(postId = params.postId, userId = params.userId)
            if (postLiked){
                postDao.updateLikesCount(postId = params.postId)
                Response.Success(
                    data = LikeResponse(success = true)
                )
            }else{
                Response.Error(
                    code = HttpStatusCode.Conflict,
                    data = LikeResponse(success = false, message = "Unexpected DB error, try again!")
                )
            }
        }
    }

    override suspend fun removeLike(params: LikeParams): Response<LikeResponse> {
        val likeExists = likesDao.isPostLikedByUser(postId = params.postId, userId = params.userId)
        return if (likeExists){
            val likeRemoved = likesDao.removeLike(postId = params.postId, userId =  params.userId)
            if (likeRemoved){
                postDao.updateLikesCount(postId = params.postId, decrement = true)
                Response.Success(
                    data = LikeResponse(success = true)
                )
            }else{
                Response.Error(
                    code = HttpStatusCode.Conflict,
                    data = LikeResponse(success = false, message = "Unexpected DB error, try again!")
                )
            }
        }else{
            Response.Error(
                code = HttpStatusCode.NotFound,
                data = LikeResponse(success = false, message = "Like not found(may be removed already)")
            )
        }
    }
}