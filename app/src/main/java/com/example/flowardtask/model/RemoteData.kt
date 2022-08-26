package com.example.flowardtask.model

import com.example.flowardtask.network.RetrofitService
import com.example.flowardtask.pojo.PostResponse
import com.example.flowardtask.pojo.PostResponseItem
import com.example.flowardtask.pojo.UserResponse
import com.example.flowardtask.pojo.UserResponseItem
import io.reactivex.rxjava3.core.Single
import retrofit2.Callback

class RemoteData(private val service: RetrofitService) : DataContract.Remote {
    override fun getUserApi(): Single<List<UserResponseItem>>{
        return service.getUserApi("posts")
    }

    override fun getPostApi(): Single<List<PostResponseItem>> {
        return service.getPostApi("users")
    }
}