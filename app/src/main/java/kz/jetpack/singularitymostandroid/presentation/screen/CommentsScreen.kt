package kz.jetpack.singularitymostandroid.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import kz.jetpack.singularitymostandroid.data.model.Comment
import kz.jetpack.singularitymostandroid.presentation.viewmodel.PostViewModel

@Composable
fun CommentsScreen(postId: String, viewModel: PostViewModel = hiltViewModel()) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val comments = viewModel.comments.filter { it.postId == postId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Comments",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(comments.size) { index ->
                val comment = comments[index]
                CommentCard(comment)
                Log.d("CommentsScreen", "Comment: ${comment.content}")
            }
        }

        LaunchedEffect(listState) {
            coroutineScope.launch {
                if (listState.firstVisibleItemIndex + listState.layoutInfo.visibleItemsInfo.size >= comments.size) {
                    viewModel.loadMoreComments()
                }
            }
        }
    }
}

@Composable
fun CommentCard(comment: Comment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = comment.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Email: ${comment.email}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = comment.content, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
