package eu.javimar.ktorsample.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.javimar.ktorsample.data.network.PostsService
import eu.javimar.ktorsample.presentation.state.PostsState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val postsService: PostsService
): ViewModel() {

    private val _postState = mutableStateOf(PostsState())
    val postState: State<PostsState> = _postState

    fun getPosts() {
        viewModelScope.launch {
            _postState.value = postState.value.copy(
                posts = postsService.getPosts()
            )
        }
    }
}