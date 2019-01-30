package me.inassar.profilefreebie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.inassar.profilefreebie.model.ProfileFriendsModel
import me.inassar.profilefreebie.model.ProfilePhotosModel
import me.inassar.profilefreebie.state.ProfileState
import me.inassar.profilefreebie.state.ScreenState
import me.inassar.profilefreebie.view.fragment.profile.ProfileItemsInteractor

class ProfileViewModel(private val profileItemsInteractor: ProfileItemsInteractor) : ViewModel() {

    private lateinit var _profileFriendsState: MutableLiveData<ScreenState<ProfileState>>
    private lateinit var _profilePhotosState: MutableLiveData<ScreenState<ProfileState>>

    val profileFriendsState: LiveData<ScreenState<ProfileState>>
        get() {
            if (!::_profileFriendsState.isInitialized) {
                _profileFriendsState = MutableLiveData()
                _profileFriendsState.value = ScreenState.Loading
                profileItemsInteractor.findFriends(::onFriendsItemsLoaded)
            }
            return _profileFriendsState
        }
    val profilePhotosState: LiveData<ScreenState<ProfileState>>
        get() {
            if (!::_profilePhotosState.isInitialized) {
                _profilePhotosState = MutableLiveData()
                _profilePhotosState.value = ScreenState.Loading
                profileItemsInteractor.findPhotos(::onPhotosItemsLoaded)
            }
            return _profilePhotosState
        }


    private fun onFriendsItemsLoaded(items: ArrayList<ProfileFriendsModel>) {
        _profileFriendsState.value = ScreenState.Render(
                ProfileState.ShowFriendsData(items))
    }

    fun onFriendsItemClicked(item: ProfileFriendsModel) {
        _profileFriendsState.value = ScreenState.Render(
                ProfileState.ShowMessage(item.friendName))
    }

    private fun onPhotosItemsLoaded(items: ArrayList<ProfilePhotosModel>) {
        _profilePhotosState.value = ScreenState.Render(
                ProfileState.ShowPhotosData(items))
    }

    fun onPhotosItemClicked(item: ProfilePhotosModel) {
        _profilePhotosState.value = ScreenState.Render(
                ProfileState.ShowMessage("Image ${item.image} clicked"))
    }

    class ProfileViewModelFactory(private val profileItemsInteractor: ProfileItemsInteractor) :
            ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ProfileViewModel(profileItemsInteractor) as T
        }
    }

}