package com.example.flowardtask.pojo

import com.google.gson.annotations.SerializedName

data class UserResponse(
	val userResponse: List<UserResponseItem?>? = null
)

data class UserResponseItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("albumId")
	val albumId: Int? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("thumbnailUrl")
	val thumbnailUrl: String? = null
)
