package com.example.flowardtask.model

import com.example.flowardtask.pojo.PostResponse
import com.example.flowardtask.pojo.UserResponse
import io.reactivex.rxjava3.core.Single

class Repository(private val remoteData: DataContract.Remote) : DataContract.Repository {
    override fun getUserApi(): Single<UserResponse> {
        return remoteData.getUserApi()
    }

    override fun getPostApi(): Single<PostResponse> {
        return remoteData.getPostApi()
    }
}