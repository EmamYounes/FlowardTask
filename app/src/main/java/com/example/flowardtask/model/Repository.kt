package com.example.flowardtask.model

import com.example.flowardtask.pojo.PostResponse
import com.example.flowardtask.pojo.PostResponseItem
import com.example.flowardtask.pojo.UserResponse
import com.example.flowardtask.pojo.UserResponseItem
import io.reactivex.rxjava3.core.Single
import retrofit2.Callback

class Repository(private val remoteData: DataContract.Remote) : DataContract.Repository {
    override fun getUserApi(): Single<List<UserResponseItem>>{
        return remoteData.getUserApi()
    }

    override fun getPostApi(): Single<List<PostResponseItem>> {
        return remoteData.getPostApi()
    }
}