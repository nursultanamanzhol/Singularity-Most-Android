package kz.jetpack.singularitymostandroid.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kz.jetpack.singularitymostandroid.data.model.Comment
import kz.jetpack.singularitymostandroid.data.model.Post
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor() : ViewModel() {
    val posts = mutableStateListOf<Post>()
    val comments = mutableStateListOf<Comment>()
    private var currentPage = 0
    private val pageSize = 10

    init {
        loadMorePosts()
        loadMoreComments()
    }

    fun loadMorePosts() {
        val newPosts = List(pageSize) {
            Post(
                id = (currentPage * pageSize + it + 1).toString(),
                title = "Post Title ${currentPage * pageSize + it + 1}",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean arcu arcu, tristique in orci sed, volutpat auctor risus. Donec convallis maximus auctor. Etiam id dui at libero porttitor mollis vel eget lacus. Nullam molestie magna at eros pretium, a elementum libero lobortis. Cras eu consectetur mi. Proin iaculis, ex vitae malesuada vehicula, erat sem aliquam leo, ac porttitor mauris turpis eu mi. Suspendisse sed est pretium, malesuada diam et, vehicula ligula. Duis consequat in purus et tincidunt. Donec odio velit, dapibus a elit a, feugiat tempor neque. Donec convallis, tellus eu vestibulum porta, mauris metus imperdiet orci, sit amet viverra leo orci sed purus. Nunc pretium quam ut vehicula pellentesque."
            )
        }
        posts.addAll(newPosts)
        currentPage++
    }

    fun loadMoreComments() {
        // Assuming we generate comments for all posts
        posts.forEach { post ->
            val newComments = List(pageSize) { index ->
                Comment(
                    id = (comments.size + index + 1).toString(),
                    postId = post.id,
                    name = "Comment Name",
                    email = "username@mail.com",
                    content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam molestie nisl ac faucibus blandit."
                )
            }
            comments.addAll(newComments)
        }
    }
}
