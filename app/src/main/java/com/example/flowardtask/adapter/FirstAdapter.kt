package com.example.flowardtask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flowardtask.OnClick
import com.example.flowardtask.R
import com.example.flowardtask.pojo.GeneralModel
import com.squareup.picasso.Picasso


class FirstAdapter(myDataset: MutableList<GeneralModel>) :
    RecyclerView.Adapter<FirstAdapter.FirstViewHolder>() {

    private var mDataset: MutableList<GeneralModel>? = null
    var callbacks: OnClick? = null


    private var viewHolder: FirstViewHolder? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FirstViewHolder {
        // create a new view
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.first_item, parent, false)

        return FirstViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FirstViewHolder, position: Int) {
        viewHolder = holder
        holder.bind(mDataset?.get(position)!!, position)
    }


    inner class FirstViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var viewID: LinearLayout = view.findViewById(R.id.view_id)
        var userThumbnail: ImageView = view.findViewById(R.id.user_thumbnail)
        var userName: TextView = view.findViewById(R.id.user_name)
        var postCount: TextView = view.findViewById(R.id.post_count)

        fun bind(model: GeneralModel, position: Int) {
            userName.text = model.userName
            postCount.text = model.totalPostCount
            Picasso.get()
                .load(model.thumbnail)
                .into(userThumbnail)
            viewID.tag = position
            viewID.setOnClickListener(this)

        }

        override fun onClick(p0: View?) {
            val model = mDataset?.get(p0?.tag as Int)
            model?.let { callbacks!!.onItemClicked(it) }
        }
    }


    override fun getItemCount(): Int {
        return mDataset!!.size
    }

    fun addList(lst: List<GeneralModel>) {
        mDataset = lst as MutableList<GeneralModel>
        notifyDataSetChanged()
    }

    fun getCurrentList(): List<GeneralModel> {
        return mDataset!!
    }

    init {
        mDataset = myDataset
    }

}
