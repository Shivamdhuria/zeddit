package com.example.zeddit.data

data class DataResponse(

    val children: List<PostResponse>,
    val after: String?,
    val before: String?
)
