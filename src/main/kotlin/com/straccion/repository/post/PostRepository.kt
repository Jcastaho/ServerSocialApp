package com.straccion.repository.post

import com.straccion.model.PostResponse
import com.straccion.model.PostTextParams
import com.straccion.model.PostsResponse
import com.straccion.util.Response

interface PostRepository {
    suspend fun createPost(imageUrl: String, postTextParams: PostTextParams): Response<PostResponse>

    suspend fun getFeedPosts(userId: Long, pageNumber: Int, pageSize: Int): Response<PostsResponse>

    suspend fun getPostsByUser(
        postsOwnerId: Long,
        currentUserId: Long,
        pageNumber: Int,
        pageSize: Int
    ): Response<PostsResponse>

    suspend fun getPost(postId: Long, currentUserId: Long): Response<PostResponse>

    suspend fun deletePost(postId: Long): Response<PostResponse>
}