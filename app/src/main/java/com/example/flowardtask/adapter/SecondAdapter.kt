package com.example.flowardtask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flowardtask.R
import com.example.flowardtask.pojo.PostResponseItem
import io.reactivex.rxjava3.annotations.Nullable


class SecondAdapter(myDataset: MutableList<PostResponseItem>) :
    RecyclerView.Adapter<SecondAdapter.SecondViewHolder>() {

    private var mDataset: MutableList<PostResponseItem>? = null

    private var viewHolder: SecondViewHolder? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SecondViewHolder {
        // create a new view
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.second_item, parent, false)

        return SecondViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SecondViewHolder, position: Int) {
        viewHolder = holder
        holder.bind(mDataset?.get(position)!!, position)
    }


    inner class SecondViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.title)
        var body: TextView = view.findViewById(R.id.body)

        fun bind(model: PostResponseItem, position: Int) {
            title.text = model.title
            body.text = model.body
        }
    }


    override fun getItemCount(): Int {
        return mDataset!!.size
    }

    fun addList(lst: @Nullable List<PostResponseItem?>?) {
        mDataset = lst as MutableList<PostResponseItem>
        notifyDataSetChanged()
    }

    fun getCurrentList(): List<PostResponseItem> {
        return mDataset!!
    }

    init {
        mDataset = myDataset
    }

}
