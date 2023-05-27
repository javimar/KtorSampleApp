package eu.javimar.ktorsample.presentation.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import eu.javimar.ktorsample.presentation.feature.state.PostsState
@Composable
fun PostListingMain(
    vm: PostListingsViewModel = hiltViewModel()
) {
    PostListingScreen(vm.postState)
}


@Composable
fun PostListingScreen(
    state: PostsState
) {
    LazyColumn {
        items(state.posts) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = it.title,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = it.body,
                    fontSize = 14.sp
                )
            }
        }
    }
}