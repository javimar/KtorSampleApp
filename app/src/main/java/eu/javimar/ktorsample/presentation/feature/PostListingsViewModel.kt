package eu.javimar.ktorsample.presentation.feature

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.javimar.ktorsample.data.network.PostsService
import eu.javimar.ktorsample.presentation.feature.state.PostsState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListingsViewModel @Inject constructor(
    private val postsService: PostsService
): ViewModel() {

    var postState by mutableStateOf(PostsState())
        private set

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            postState = postState.copy(
                posts = postsService.getPosts()
            )
        }
    }
}