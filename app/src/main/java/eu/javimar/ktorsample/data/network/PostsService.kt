package eu.javimar.ktorsample.data.network

import eu.javimar.ktorsample.data.dto.PostRequest
import eu.javimar.ktorsample.data.dto.PostResponse

interface PostsService {
    suspend fun getPosts(): List<PostResponse>
    suspend fun createPost(postRequest: PostRequest): PostResponse?
}