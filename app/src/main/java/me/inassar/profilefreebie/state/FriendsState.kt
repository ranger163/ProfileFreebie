package me.inassar.profilefreebie.state

import me.inassar.profilefreebie.model.FriendsModel

sealed class FriendsState {
    class ShowFriendsData(val items: ArrayList<FriendsModel>) : FriendsState()
    class HandleFriendItemClick(val item: FriendsModel) : FriendsState()
    class HandleFollowBtnClick(val item: FriendsModel) : FriendsState()
}