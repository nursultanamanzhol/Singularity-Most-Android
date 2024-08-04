package kz.jetpack.singularitymostandroid.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import kz.jetpack.singularitymostandroid.presentation.PostCard
import kz.jetpack.singularitymostandroid.presentation.viewmodel.PostViewModel

@Composable
fun PostsScreen(viewModel: PostViewModel, navController: NavHostController) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(viewModel.posts.size) { index ->
            val post = viewModel.posts[index]
            PostCard(post = post, onClick = {
                navController.navigate("postDetails/${post.id}")
            })
        }
    }

    LaunchedEffect(listState) {
        coroutineScope.launch {
            if (listState.firstVisibleItemIndex + listState.layoutInfo.visibleItemsInfo.size >= viewModel.posts.size) {
                viewModel.loadMorePosts()
            }
        }
    }
}
