package com.straccion.repository.post_comments

import com.straccion.model.CommentResponse
import com.straccion.model.GetCommentsResponse
import com.straccion.model.NewCommentParams
import com.straccion.model.RemoveCommentParams
import com.straccion.util.Response

interface PostCommentsRepository {
    suspend fun addComment(params: NewCommentParams): Response<CommentResponse>

    suspend fun removeComment(params: RemoveCommentParams): Response<CommentResponse>

    suspend fun getPostComments(postId: Long, pageNumber: Int, pageSize: Int): Response<GetCommentsResponse>
}