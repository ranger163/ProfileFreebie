package me.inassar.profilefreebie.state

import me.inassar.profilefreebie.model.ProfileFriendsModel
import me.inassar.profilefreebie.model.ProfilePhotosModel

sealed class ProfileState {
    class ShowFriendsData(val items: ArrayList<ProfileFriendsModel>) : ProfileState()
    class ShowPhotosData(val items: ArrayList<ProfilePhotosModel>) : ProfileState()
    class ShowMessage(val message: String) : ProfileState()
}