package com.example.flowardtask.viewmodel

import androidx.lifecycle.ViewModel
import com.example.flowardtask.model.Repository
import com.example.flowardtask.pojo.GeneralModel
import com.example.flowardtask.pojo.PostResponseItem
import com.example.flowardtask.pojo.UserResponseItem
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MyViewModel(
    private val repository: Repository
) : ViewModel() {

    var errorGetUserApi = BehaviorRelay.create<String>()
    var successGetUserApi = BehaviorRelay.create<List<UserResponseItem>>()


    var errorGetPostApi = BehaviorRelay.create<String>()
    var successGetPostApi = BehaviorRelay.create<List<PostResponseItem>>()


    var successGetApi = BehaviorRelay.create<List<GeneralModel>>()
    var errorGetApi = BehaviorRelay.create<String>()
    var postList: BehaviorRelay<List<PostResponseItem?>> = BehaviorRelay.create()
    var postImage = BehaviorRelay.create<String>()

    val compositeDisposable = CompositeDisposable()
    fun callUserApi() {
        repository.getUserApi()
            .doOnError { errorGetUserApi.accept(it.message!!.toString()) }
            .subscribe({
                it.let { response ->
                    successGetUserApi.accept(response)
                }
            }, { throwable ->
                errorGetUserApi.accept(throwable.message!!.toString())
            })
    }

    fun callPostApi() {
        repository.getPostApi()
            .doOnError { errorGetPostApi.accept(it.message!!.toString()) }
            .subscribe({
                it.let { response ->
                    successGetPostApi.accept(response)
                }
            }, { throwable ->
                errorGetPostApi.accept(throwable.message!!.toString())
            })
    }

    fun callApi() {
        successGetApi = BehaviorRelay.create()
        val disposable = Observable.zip(
            repository.getUserApi().toObservable().subscribeOn(Schedulers.io()),
            repository.getPostApi().toObservable().subscribeOn(Schedulers.io())
        ) { firstResponse: List<UserResponseItem>,
            secondResponse: List<PostResponseItem> ->
            firstResponse.map {
                val postList = secondResponse.filter { postItem ->
                    postItem.userId!! == it.userId
                }
                val model = GeneralModel()
                model.userName = it.name.toString()
                model.thumbnail = it.thumbnailUrl.toString()
                model.image = it.url.toString()
                model.totalPostCount = postList.size.toString()
                model.postList = postList
                model
            }
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                successGetApi.accept(it)
            }, { throwable ->
                errorGetApi.accept(throwable.message!!.toString())
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}