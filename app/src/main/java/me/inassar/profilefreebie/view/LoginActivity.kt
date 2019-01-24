package me.inassar.profilefreebie.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*
import me.inassar.profilefreebie.R
import me.inassar.profilefreebie.ScreenState
import me.inassar.profilefreebie.model.LoginState
import me.inassar.profilefreebie.toast
import me.inassar.profilefreebie.toolbar
import me.inassar.profilefreebie.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        interactions()
    }

    private fun init() {
        toolbar(toolBar, getString(R.string.login), false) {
            toast("Sign up clicked")
        }
        viewModel = ViewModelProviders.of(
                this,
                LoginViewModel.LoginViewModelFactory(LoginInteractor())
        )[LoginViewModel::class.java]
        viewModel.loginState.observe(::getLifecycle, ::updateUi)
    }

    private fun interactions() {
        loginBtn.setOnClickListener {
            viewModel.onLoginClicked(
                    emailEt.editText?.text.toString(),
                    passwordEt.editText?.text.toString())
        }
        forgotPasswordTv.setOnClickListener { toast("Forgot password clicked") }
    }

    private fun updateUi(screenState: ScreenState<LoginState>?) {
        when (screenState) {
            ScreenState.Loading -> progress.visibility = View.VISIBLE
            is ScreenState.Render -> processLoginState(screenState.renderState)
        }
    }

    private fun processLoginState(renderState: LoginState) {
        progress.visibility = View.GONE
        when (renderState) {
            LoginState.SUCCESS -> toast("Success")
            LoginState.WRONG_EMAIL -> emailEt.error = getString(R.string.invalid_email_address)
            LoginState.WRONG_PASSWORD -> passwordEt.error = getString(R.string.invalid_password)
        }
    }
}
