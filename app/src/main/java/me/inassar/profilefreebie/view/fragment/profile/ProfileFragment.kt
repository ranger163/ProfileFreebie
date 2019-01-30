package me.inassar.profilefreebie.view.fragment.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.loading_layout.*
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.android.synthetic.main.profile_friends_section_layout.*
import kotlinx.android.synthetic.main.profile_photos_section_layout.*
import me.inassar.profilefreebie.R
import me.inassar.profilefreebie.resize
import me.inassar.profilefreebie.state.ProfileState
import me.inassar.profilefreebie.state.ScreenState
import me.inassar.profilefreebie.toast
import me.inassar.profilefreebie.view.adapter.ProfileFriendsAdapter
import me.inassar.profilefreebie.view.adapter.ProfilePhotosAdapter
import me.inassar.profilefreebie.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {

    private lateinit var friendsAdapter: ProfileFriendsAdapter
    private lateinit var photosAdapter: ProfilePhotosAdapter
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        aboutUserTv.resize()
        aboutUserTv.setOnClickListener {
            aboutUserTv.maxLines = if (aboutUserTv.lineCount == 3) {
                40
            } else {
                3
            }
        }
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this,
                ProfileViewModel.ProfileViewModelFactory(
                        ProfileItemsInteractor())
        )[ProfileViewModel::class.java]
        viewModel.profileFriendsState.observe(::getLifecycle, ::updateUi)
        viewModel.profilePhotosState.observe(::getLifecycle, ::updateUi)
    }

    private fun updateUi(screenState: ScreenState<ProfileState>) {
        when (screenState) {
            ScreenState.Loading -> progress.visibility = View.VISIBLE
            is ScreenState.Render -> processProfileStateData(screenState.renderState)
        }
    }

    private fun processProfileStateData(renderState: ProfileState) {
        when (renderState) {
            is ProfileState.ShowFriendsData -> {
                progress.visibility = View.GONE
                friendsRecycler.apply {
                    visibility = View.VISIBLE
                    friendsAdapter = ProfileFriendsAdapter(renderState.items,
                            viewModel::onFriendsItemClicked)
                    adapter = friendsAdapter
                }
            }
            is ProfileState.ShowPhotosData -> {
                progress.visibility = View.GONE
                photosRecycler.apply {
                    visibility = View.VISIBLE
                    photosAdapter = ProfilePhotosAdapter(renderState.items,
                            viewModel::onPhotosItemClicked)
                    adapter = photosAdapter
                }
            }
            is ProfileState.ShowMessage -> activity?.toast(renderState.message)
        }
    }

}
