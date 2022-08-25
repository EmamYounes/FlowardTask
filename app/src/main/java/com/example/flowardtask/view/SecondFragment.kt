package com.example.flowardtask.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowardtask.R
import com.example.flowardtask.adapter.SecondAdapter
import com.example.flowardtask.viewmodel.MyViewModel
import com.example.flowardtask.viewmodel.MyViewModelFactory
import com.squareup.picasso.Picasso
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class SecondFragment : BaseFragment(), KodeinAware {

    override val kodein by kodein()

    private val factory: MyViewModelFactory by instance()

    private val viewModel: MyViewModel by instance()
    private var recyclerview: RecyclerView? = null
    private var imageView: ImageView? = null

    private var secondAdapter = SecondAdapter(mutableListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.second_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        bindUI()
    }

    private fun init() {
        showLoading()
        recyclerview = view?.findViewById(R.id.recyclerview)
        imageView = view?.findViewById(R.id.image_view)
    }


    private fun bindUI() {
        initRecyclerView()
        Picasso.get()
            .load(viewModel.postImage.value)
            .into(imageView)
    }


    private fun initRecyclerView() {
        recyclerview?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = secondAdapter
        }
        viewModel.postList.value.let { secondAdapter.addList(it) }
        hideLoading()
    }
}
