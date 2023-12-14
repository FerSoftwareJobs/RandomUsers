package com.develofer.randomusers.ui.userlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.develofer.randomusers.R
import com.develofer.randomusers.databinding.ItemUserListBinding
import com.develofer.randomusers.domain.data.UserDomain

class UserListAdapter(private val context: Context, private val onItemClick: (UserDomain) -> Unit) :
    ListAdapter<UserDomain, UserListAdapter.UserViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user_list, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemUserListBinding.bind(itemView)

        fun bind(userDomain: UserDomain) {
            binding.apply {
                tvItemUserListUserName.text = context.getString(
                    R.string.user_list_adapter__user_name_and_surname,
                    userDomain.name.first,
                    userDomain.name.last
                )
                tvItemUserListUserEmail.text = userDomain.email
                Glide.with(context)
                    .load(userDomain.picture.medium)
                    .into(ivItemUserListUserImage)
                itemView.setOnClickListener { onItemClick(userDomain) }
            }
        }
    }

    private class CharacterDiffCallback : DiffUtil.ItemCallback<UserDomain>() {
        override fun areItemsTheSame(oldItem: UserDomain, newItem: UserDomain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserDomain, newItem: UserDomain): Boolean {
            return oldItem == newItem
        }
    }
}