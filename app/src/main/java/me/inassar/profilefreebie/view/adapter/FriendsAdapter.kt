package me.inassar.profilefreebie.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.friends_item_row.view.*
import me.inassar.profilefreebie.R
import me.inassar.profilefreebie.model.FriendsModel

class FriendsAdapter(
        private val itemData: ArrayList<FriendsModel>,
        private val itemClick: (FriendsModel) -> Unit,
        private val followBtnClick: (FriendsModel) -> Unit) :
        RecyclerView.Adapter<FriendsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.friends_item_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (itemData.isNotEmpty())
            itemData.size
        else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemData[position], itemClick, followBtnClick)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: FriendsModel, itemClick: (FriendsModel) -> Unit,
                 followBtnClick: (FriendsModel) -> Unit) {
            // UI Setting Code
            itemView.friendNameTv.text = item.name
            itemView.friendsSpecialityTv.text = item.speciality
            itemView.friendsFollowersTv.text = "${item.followers}" +
                    " ${itemView.context.resources.getString(R.string.followers)}"
            itemView.friendsFollowingsTv.text = "${item.followings}" +
                    " ${itemView.context.resources.getString(R.string.following)}"
            if (!item.isPro)
                itemView.friendTypeTV.visibility = View.GONE
            if (item.isFollowed)
                itemView.friendsFollowBtn.apply {
                    text = itemView.context.resources.getString(R.string.followed)
                    background = itemView.context.resources.getDrawable(R.drawable.grey_btn_bg)
                    setTextColor(Color.parseColor("#303F9F"))
                }
            itemView.setOnClickListener { itemClick(item) }
            itemView.friendsFollowBtn.setOnClickListener { followBtnClick(item) }
        }
    }
}
