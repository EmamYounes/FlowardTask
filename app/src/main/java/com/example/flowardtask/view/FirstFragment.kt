package com.example.flowardtask.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowardtask.OnClick
import com.example.flowardtask.R
import com.example.flowardtask.adapter.FirstAdapter
import com.example.flowardtask.pojo.GeneralModel
import com.example.flowardtask.viewmodel.MyViewModel
import com.example.flowardtask.viewmodel.MyViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class FirstFragment : BaseFragment(), KodeinAware, OnClick {

    override val kodein by kodein()

    private val factory: MyViewModelFactory by instance()

    private val viewModel: MyViewModel by instance()
    private var recyclerview: RecyclerView? = null

    private var firstAdapter = FirstAdapter(mutableListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        bindUI()
    }

    private fun init() {
        firstAdapter.callbacks = this
        recyclerview = view?.findViewById(R.id.recyclerview)
    }


    private fun bindUI() {
        showLoading()
        initRecyclerView()
        viewModel.callApi()
        viewModel.successGetApi.subscribe {
            firstAdapter.addList(it)
            hideLoading()
        }

        viewModel.errorGetApi.subscribe {

            Log.e("errorGetApi", it)
            hideLoading()
        }
    }


    private fun initRecyclerView() {
        recyclerview?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = firstAdapter
        }

    }

    override fun onItemClicked(item: GeneralModel) {
        viewModel.postList.accept(item.postList)
        viewModel.postImage.accept(item.image)
        val navController =
            activity?.findNavController(R.id.my_navigation)
        navController?.backQueue?.clear()
        navController?.navigate(R.id.secondFragment, bundleOf(), options)
    }
}
