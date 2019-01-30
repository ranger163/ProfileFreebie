package me.inassar.profilefreebie.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.inassar.profilefreebie.R
import me.inassar.profilefreebie.model.ProfilePhotosModel

class ProfilePhotosAdapter(
        private val itemData: ArrayList<ProfilePhotosModel>,
        private val itemClick: (ProfilePhotosModel) -> Unit) :
        RecyclerView.Adapter<ProfilePhotosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.profile_photos_item_row, parent, false)
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
        fun bind(item: ProfilePhotosModel, itemClick: (ProfilePhotosModel) -> Unit) {
            // UI Setting Code
            itemView.setOnClickListener { itemClick(item) }
        }
    }
}
