package com.example.task

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task.databinding.InflateProfileListBinding


class ProfileListAdapter : RecyclerView.Adapter<ProfileViewHolder>() {
    private var listener: ItemClickListener? = null
    private var listenerNew: ItemNewClickListener? = null


    var profileList = mutableListOf<ProfileData>()
    fun setMovieList(profileList: List<ProfileData>) {
        this.profileList = profileList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = InflateProfileListBinding.inflate(inflater, parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val profile = profileList[position]
        holder.binding.tvProfileName.text = profile.name
        holder.binding.tvProfileAddress.text = profile.desc

        Glide.with(holder.itemView.context).load(profile.imageUrl).into(holder.binding.ivProfile)

        holder.itemView.setOnClickListener {

            listener!!.onClick(it,position)
        }

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

    interface ItemClickListener {
        fun onClick(view: View?, position: Int)
    }

    interface ItemNewClickListener {
        fun onNewClick(view: View?, position: Int,value:String)
    }

    fun setClickListener(itemClickListener: ItemClickListener) {
        this.listener = itemClickListener
    }

    fun setNewClickListener(itemNewClickListener: ItemNewClickListener) {
        this.listenerNew = itemNewClickListener
    }
}
class ProfileViewHolder(val binding: InflateProfileListBinding): RecyclerView.ViewHolder(binding.root) {

}