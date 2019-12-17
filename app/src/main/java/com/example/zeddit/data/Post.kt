package com.example.zeddit.data

data class Post(
    val author: String,
    val title: String,
    val num_comments: Int,
    val created: Long,
    val thumbnail: String,
    val url: String?,
    val isVideo: Boolean
)

data class PostResponse(
    val kind: String,
    val Post: Post
)