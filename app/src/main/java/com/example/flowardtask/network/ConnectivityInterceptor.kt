package com.example.flowardtask.network

import android.app.Activity
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(var activity: Activity) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!CheckInternetConnection.isOnline(activity.applicationContext)) {
            throw Exception()
        } else {
            chain.proceed(chain.request())
        }

    }

}