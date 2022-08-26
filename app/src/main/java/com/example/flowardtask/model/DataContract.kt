package com.example.flowardtask.model

import com.example.flowardtask.pojo.PostResponse
import com.example.flowardtask.pojo.PostResponseItem
import com.example.flowardtask.pojo.UserResponse
import com.example.flowardtask.pojo.UserResponseItem
import io.reactivex.rxjava3.core.Single
import retrofit2.Callback

interface DataContract {

    interface Repository {
        fun getUserApi(): Single<List<UserResponseItem>>
        fun getPostApi(): Single<List<PostResponseItem>>
    }

    interface Remote {
        fun getUserApi(): Single<List<UserResponseItem>>
        fun getPostApi(): Single<List<PostResponseItem>>
    }
}