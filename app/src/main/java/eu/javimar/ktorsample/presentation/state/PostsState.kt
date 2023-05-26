package eu.javimar.ktorsample.presentation.state

import eu.javimar.ktorsample.data.dto.PostResponse

data class PostsState(
    val posts: List<PostResponse> = emptyList()
)
