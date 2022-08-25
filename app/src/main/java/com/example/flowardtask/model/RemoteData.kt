package com.example.flowardtask.model

import com.example.flowardtask.network.RetrofitService
import com.example.flowardtask.pojo.PostResponse
import com.example.flowardtask.pojo.UserResponse
import io.reactivex.rxjava3.core.Single

class RemoteData(private val service: RetrofitService) : DataContract.Remote {
    override fun getUserApi(): Single<UserResponse> {
        return service.getUserApi("posts")
    }

    override fun getPostApi(): Single<PostResponse> {
        return service.getPostApi("users")
    }
}