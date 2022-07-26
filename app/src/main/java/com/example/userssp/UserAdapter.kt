package com.example.userssp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.userssp.databinding.ItemUserAltBinding


class UserAdapter(val users: List<User>) : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_user_alt, parent, false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user = users[position]
        with(holder) {
            itemUserAltBinding.tvOrder.text = (position + 1).toString()
            itemUserAltBinding.tvName.text = user.getFullName()
            Glide.with(context)
                .load(user.url).diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(itemUserAltBinding.imgPhoto)
        }
    }

    override fun getItemCount(): Int = users.size


    inner class UserHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemUserAltBinding = ItemUserAltBinding.bind(view)

    }
}