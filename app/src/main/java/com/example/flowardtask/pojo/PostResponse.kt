package com.example.flowardtask.pojo

import com.google.gson.annotations.SerializedName

data class PostResponse(
    val postResponse: List<PostResponseItem?>? = null
)

data class PostResponseItem(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("body")
    val body: String? = null,

    @field:SerializedName("userId")
    val userId: Int? = null
)
