package com.example.flowardtask.network

import com.example.flowardtask.pojo.PostResponse
import com.example.flowardtask.pojo.UserResponse
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import java.util.concurrent.TimeUnit

interface RetrofitService {


    @GET
    fun getUserApi(
        @Url url: String
    ): Single<UserResponse>

    @GET
    fun getPostApi(
        @Url url: String
    ): Single<PostResponse>

    companion object {

        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor,
            responseInterceptor: ResponseInterceptor
        ): RetrofitService {

            return Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/SharminSirajudeen/test_resources/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(getHttpClient(networkConnectionInterceptor, responseInterceptor))
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHttpClient(networkConnectionInterceptor, responseInterceptor))
                .build()
                .create(RetrofitService::class.java)
        }

        private fun getHttpClient(
            networkConnectionInterceptor: NetworkConnectionInterceptor,
            responseInterceptor: ResponseInterceptor
        ): OkHttpClient {
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
                .addInterceptor(networkConnectionInterceptor)
                .addInterceptor(responseInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
            return builder.build()
        }
    }


}