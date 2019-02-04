package me.inassar.profilefreebie.view.fragment.friends

import me.inassar.profilefreebie.model.FriendsModel
import me.inassar.profilefreebie.postDelayed
import kotlin.random.Random

class FriendsInteractor {
    fun getFriendsData(callback: (ArrayList<FriendsModel>) -> Unit) {
        postDelayed(2000) { callback(createFriendsList()) }
    }

    private fun createFriendsList(): ArrayList<FriendsModel> {
        val data: ArrayList<FriendsModel>? = ArrayList()
        for (i in 0..10) {
            data?.add(
                    FriendsModel(
                            i,
                            "Name $i",
                            "Speciality $i",
                            Random.nextInt(0, 200),
                            Random.nextInt(0, 300),
                            Random.nextBoolean(),
                            Random.nextBoolean()
                    )
            )
        }
        return data!!
    }
}