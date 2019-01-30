package me.inassar.profilefreebie.view.fragment.profile

import me.inassar.profilefreebie.model.ProfileFriendsModel
import me.inassar.profilefreebie.model.ProfilePhotosModel
import me.inassar.profilefreebie.postDelayed

class ProfileItemsInteractor {
    fun findFriends(callback: (ArrayList<ProfileFriendsModel>) -> Unit) {
        postDelayed(2000) { callback(createFriendsList()) }
    }

    fun findPhotos(callback: (ArrayList<ProfilePhotosModel>) -> Unit) {
        postDelayed(2000) { callback(createPhotosList()) }
    }

    private fun createPhotosList(): ArrayList<ProfilePhotosModel> {
        val photosData: ArrayList<ProfilePhotosModel>? = ArrayList()
        for (i in 1..5) {
            photosData?.add(ProfilePhotosModel(i))
        }
        return photosData!!
    }

    private fun createFriendsList(): ArrayList<ProfileFriendsModel> {
        val friendsData: ArrayList<ProfileFriendsModel>? = ArrayList()
        for (i in 1..5) {
            friendsData?.add(ProfileFriendsModel("Ahmed $i", 0))
        }
        return friendsData!!
    }
}