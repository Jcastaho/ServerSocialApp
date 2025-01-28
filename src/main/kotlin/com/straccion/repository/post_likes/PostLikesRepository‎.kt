package com.straccion.repository.post_likes

import com.straccion.model.LikeParams
import com.straccion.model.LikeResponse
import com.straccion.util.Response

interface PostLikesRepository {
    suspend fun addLike(params: LikeParams): Response<LikeResponse>

    suspend fun removeLike(params: LikeParams): Response<LikeResponse>
}