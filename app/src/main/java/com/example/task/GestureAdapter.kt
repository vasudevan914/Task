package com.example.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task.databinding.InflateGestureListBinding

class GestureAdapter : RecyclerView.Adapter<GestureAdapter.GestureViewHolder>() {
    private var listenerNew:ItemNewClickListener? = null

    var profileList = mutableListOf<ProfileData>()
    fun setMovieList(profileList: List<ProfileData>) {
        this.profileList = profileList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GestureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = InflateGestureListBinding.inflate(inflater, parent, false)
        return GestureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GestureViewHolder, position: Int) {
        val profile = profileList[position]
        holder.binding.tvProfileName.text = profile.name
        holder.binding.tvProfileAddress.text = profile.desc

        Glide.with(holder.itemView.context).load(profile.imageUrl).into(holder.binding.ivProfile)


        holder.binding.btYes.setOnClickListener {
            listenerNew!!.onNewClick(it,position,"Yes")

        }
        holder.binding.btNo.setOnClickListener {
            listenerNew!!.onNewClick(it,position,"No")

        }

    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    interface ItemNewClickListener {
        fun onNewClick(view: View?, position: Int, value:String)
    }



    fun setNewClickListener(itemNewClickListener: ItemNewClickListener) {
        this.listenerNew = itemNewClickListener
    }
    class GestureViewHolder(val binding: InflateGestureListBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}