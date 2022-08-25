package com.example.flowardtask.model

import com.example.flowardtask.pojo.PostResponse
import com.example.flowardtask.pojo.UserResponse
import io.reactivex.rxjava3.core.Single

interface DataContract {

    interface Repository {
        fun getUserApi(): Single<UserResponse>
        fun getPostApi(): Single<PostResponse>
    }

    interface Remote {
        fun getUserApi(): Single<UserResponse>
        fun getPostApi(): Single<PostResponse>
    }
}