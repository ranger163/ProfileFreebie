package me.inassar.profilefreebie

import android.content.Context
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.toolbar.view.*

fun postDelayed(delayMillis: Long, task: () -> Unit) {
    Handler().postDelayed(task, delayMillis)
}

fun AppCompatActivity.toolbar(toolbar: Toolbar, title: String, isSearch: Boolean, task: () -> Unit) {
    setSupportActionBar(toolbar)
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
    toolbar.toolBarTitleTv.text = title
    toolbar.setNavigationOnClickListener { finish() }
    if (isSearch) {
        toolbar.toolBarBtn.visibility = View.GONE
        toolbar.toolBarSearch.visibility = View.VISIBLE
        toolbar.toolBarSearch.setOnClickListener { task() }
    } else {
        toolbar.toolBarBtn.visibility = View.VISIBLE
        toolbar.toolBarSearch.visibility = View.GONE
        toolbar.toolBarBtn.setOnClickListener { task() }
    }
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}