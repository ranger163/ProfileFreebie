package me.inassar.profilefreebie.view.fragment.friends

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.android.synthetic.main.loading_layout.*
import me.inassar.profilefreebie.R
import me.inassar.profilefreebie.model.FriendsModel
import me.inassar.profilefreebie.state.FriendsState
import me.inassar.profilefreebie.state.ScreenState
import me.inassar.profilefreebie.toast
import me.inassar.profilefreebie.view.adapter.FriendsAdapter
import me.inassar.profilefreebie.viewmodel.FriendsViewModel

class FriendsFragment : Fragment() {

    private lateinit var friendsAdapter: FriendsAdapter
    private lateinit var viewModel: FriendsViewModel
    private val originalDataList: ArrayList<FriendsModel> = ArrayList()
    private val displayedDataList: ArrayList<FriendsModel> = ArrayList()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.friends_menu, menu)
        val searchItem = menu.findItem(R.id.app_bar_search)

        if (searchItem != null) {

            val searchView = searchItem.actionView as SearchView
            searchView.queryHint = "Search by name..."

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    displayedDataList.clear()
                    if (newText!!.isNotEmpty()) {
                        val searchText = newText.toLowerCase()
                        originalDataList.forEach {
                            if (it.name.toLowerCase().contains(searchText))
                                displayedDataList.add(it)
                        }
                    } else {
                        displayedDataList.addAll(originalDataList)
                    }
                    friendsRecycler.adapter?.notifyDataSetChanged()
                    return true
                }

            })
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_bar_search -> {
                true
            }
            else -> false
        }
    }

    private fun init() {
        viewModel = ViewModelProviders.of(
                this,
                FriendsViewModel.FriendsViewModelFactory(FriendsInteractor())
        )[FriendsViewModel::class.java]
        viewModel.friendsState.observe(::getLifecycle, ::updateUi)
        segmentedControl.setSelectedSegment(0)
    }

    private fun updateUi(screenState: ScreenState<FriendsState>?) {
        when (screenState) {
            ScreenState.Loading -> progress.visibility = View.VISIBLE
            is ScreenState.Render -> processFriendsDataState(screenState.renderState)
        }
    }

    private fun processFriendsDataState(renderState: FriendsState) {
        when (renderState) {
            is FriendsState.ShowFriendsData -> {
                progress.visibility = View.GONE
                originalDataList.addAll(renderState.items)
                displayedDataList.addAll(renderState.items)
                friendsAdapter = FriendsAdapter(displayedDataList,
                        viewModel::onFriendsItemClicked, viewModel::onFollowBtnClicked)
                friendsRecycler.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = friendsAdapter
                }
            }
            is FriendsState.HandleFriendItemClick ->
                activity?.toast("You clicked ${renderState.item.name}")
            is FriendsState.HandleFollowBtnClick ->
                activity?.toast("You followed ${renderState.item.name}")
        }
    }

}
