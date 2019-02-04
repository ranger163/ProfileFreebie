package me.inassar.profilefreebie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.inassar.profilefreebie.model.FriendsModel
import me.inassar.profilefreebie.state.FriendsState
import me.inassar.profilefreebie.state.ScreenState
import me.inassar.profilefreebie.view.fragment.friends.FriendsInteractor

class FriendsViewModel(private val friendsInterActor: FriendsInteractor) : ViewModel() {

    private lateinit var _friendsState: MutableLiveData<ScreenState<FriendsState>>
    val friendsState: MutableLiveData<ScreenState<FriendsState>>
        get() {
            if (!::_friendsState.isInitialized) {
                _friendsState = MutableLiveData()
                _friendsState.value = ScreenState.Loading
                friendsInterActor.getFriendsData(::onFriendsDataLoaded)
            }
            return _friendsState
        }

    private fun onFriendsDataLoaded(items: ArrayList<FriendsModel>) {
        _friendsState.value = ScreenState.Render(FriendsState.ShowFriendsData(items))
    }

    fun onFriendsItemClicked(item: FriendsModel) {
        _friendsState.value = ScreenState.Render(FriendsState.HandleFriendItemClick(item))
    }

    fun onFollowBtnClicked(item: FriendsModel) {
        _friendsState.value = ScreenState.Render(FriendsState.HandleFollowBtnClick(item))
    }

    class FriendsViewModelFactory(private val friendsInteractor: FriendsInteractor) :
            ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return FriendsViewModel(friendsInteractor) as T
        }
    }
}