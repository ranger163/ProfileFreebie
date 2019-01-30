package me.inassar.profilefreebie.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.profile_friends_item_row.view.*
import me.inassar.profilefreebie.R
import me.inassar.profilefreebie.model.ProfileFriendsModel

class ProfileFriendsAdapter(
        private val itemData: ArrayList<ProfileFriendsModel>,
        private val itemClick: (ProfileFriendsModel) -> Unit) :
        RecyclerView.Adapter<ProfileFriendsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.profile_friends_item_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (itemData.isNotEmpty())
            itemData.size
        else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemData[position], itemClick)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ProfileFriendsModel, itemClick: (ProfileFriendsModel) -> Unit) {
            // UI Setting Code
            itemView.friendsNameTv.text = item.friendName
            itemView.setOnClickListener { itemClick(item) }
        }
    }
}
