package me.inassar.profilefreebie.view.activity.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*
import me.inassar.profilefreebie.R
import me.inassar.profilefreebie.toolbar

class HomeActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    toolBar.toolBarTitleTv.text = getString(R.string.title_home)
                    findNavController(R.id.home_nav_host).navigate(R.id.homeFragmentDest)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_search -> {
                    toolBar.toolBarTitleTv.text = getString(R.string.title_search)
                    findNavController(R.id.home_nav_host).navigate(R.id.searchFragmentDest)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_friends -> {
                    toolBar.toolBarTitleTv.text = getString(R.string.title_friends)
                    findNavController(R.id.home_nav_host).navigate(R.id.friendsFragmentDest)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    toolBar.toolBarTitleTv.text = getString(R.string.title_profile)
                    findNavController(R.id.home_nav_host).navigate(R.id.profileFragmentDest)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        toolbar(toolBar, getString(R.string.title_home), this) {}
    }
}
