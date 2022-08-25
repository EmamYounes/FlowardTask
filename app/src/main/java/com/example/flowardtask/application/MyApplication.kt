package com.example.flowardtask.application

import android.app.Application
import com.example.flowardtask.model.RemoteData
import com.example.flowardtask.model.Repository
import com.example.flowardtask.network.NetworkConnectionInterceptor
import com.example.flowardtask.network.ResponseInterceptor
import com.example.flowardtask.network.RetrofitService
import com.example.flowardtask.viewmodel.MyViewModel
import com.example.flowardtask.viewmodel.MyViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class MyApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ResponseInterceptor() }
        bind() from singleton { RetrofitService(instance(), instance()) }
        bind() from singleton { RemoteData(instance()) }
        bind() from singleton { Repository(instance()) }
        bind() from singleton { MyViewModelFactory(instance()) }
        bind() from singleton { MyViewModel(instance()) }
    }
}