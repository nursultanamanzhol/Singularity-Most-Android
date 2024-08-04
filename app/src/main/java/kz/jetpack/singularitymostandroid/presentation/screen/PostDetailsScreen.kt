package kz.jetpack.singularitymostandroid.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import kz.jetpack.singularitymostandroid.presentation.viewmodel.PostViewModel
import android.util.Log
import androidx.compose.ui.Alignment

@Composable
fun PostDetailsScreen(postId: String, navController: NavHostController) {
    val viewModel: PostViewModel = hiltViewModel()
    val post = viewModel.posts.find { it.id == postId }
    val comments = viewModel.comments.filter { it.postId == postId }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        post?.let {
            Text(text = it.title, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "By: Firstname Lastname",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = it.content, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Comments", style = MaterialTheme.typography.titleMedium)
                TextButton(
                    onClick = {
                        navController.navigate("comments/$postId")
                    })
                {
                    Text(text = "Show All", color = MaterialTheme.colorScheme.primary)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            comments.take(3).forEach { comment ->
                CommentCard(comment)
                Log.d("PostDetailsScreen", "Comment: ${comment.content}")
            }

            Spacer(modifier = Modifier.height(8.dp))
        } ?: run {
            Log.d("PostDetailsScreen", "Post not found for id: $postId")
        }
    }
}
