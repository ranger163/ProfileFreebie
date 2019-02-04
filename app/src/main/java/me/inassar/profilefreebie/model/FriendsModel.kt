package me.inassar.profilefreebie.model

data class FriendsModel(
    var image: Int,
    var name: String,
    var speciality: String,
    var followers: Int,
    var followings: Int,
    var isPro: Boolean,
    var isFollowed: Boolean
)