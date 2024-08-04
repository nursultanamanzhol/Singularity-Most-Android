package kz.jetpack.singularitymostandroid.data.model

data class Comment(
    val id: String,
    val postId: String,
    val name: String,
    val email: String,
    val content: String
)
