package me.inassar.profilefreebie

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Handler
import android.text.Html.fromHtml
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.toolbar.view.*
import me.inassar.profilefreebie.view.activity.home.HomeActivity
import me.inassar.profilefreebie.view.activity.login.LoginActivity

fun postDelayed(delayMillis: Long, task: () -> Unit) {
    Handler().postDelayed(task, delayMillis)
}

fun AppCompatActivity.toolbar(toolbar: Toolbar, title: String, context: Context, task: () -> Unit) {
    setSupportActionBar(toolbar)
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
    toolbar.toolBarTitleTv.text = title
    toolbar.setNavigationOnClickListener { finish() }
    when (context) {
        is LoginActivity -> {
            toolbar.toolBarBtn.visibility = View.VISIBLE
            toolbar.toolBarSearch.visibility = View.GONE
            toolbar.toolBarBtn.setOnClickListener { task() }
        }
        is HomeActivity -> {
            toolbar.toolBarBtn.visibility = View.GONE
            toolbar.toolBarSearch.visibility = View.INVISIBLE
        }
    }
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

@SuppressLint("SetTextI18n")
fun TextView.resize() {
    if (lineCount < 3) {
        text = if (Build.VERSION.SDK_INT >= 24)
            fromHtml("${text!!} <font color='blue'>less</font>", 0)
        else
            fromHtml("${text!!} <font color='blue'>less</font>")
    }
}