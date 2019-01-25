package me.inassar.profilefreebie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.inassar.profilefreebie.ScreenState
import me.inassar.profilefreebie.model.LoginState
import me.inassar.profilefreebie.view.activity.login.LoginInteractor

class LoginViewModel(val loginInteractor: LoginInteractor) : ViewModel(),
        LoginInteractor.onLoginFinishedListener {

    private val _loginState = MutableLiveData<ScreenState<LoginState>>()
    val loginState: LiveData<ScreenState<LoginState>>
        get() = _loginState


    fun onLoginClicked(email: String, password: String) {
        _loginState.value = ScreenState.Loading
        loginInteractor.login(email, password, this)
    }

    override fun onEmailError() {
        _loginState.value = ScreenState.Render(LoginState.WRONG_EMAIL)
    }

    override fun onPasswordError() {
        _loginState.value = ScreenState.Render(LoginState.WRONG_PASSWORD)
    }

    override fun onSuccess() {
        _loginState.value = ScreenState.Render(LoginState.SUCCESS)
    }

    class LoginViewModelFactory(private val loginInteractor: LoginInteractor) :
            ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(loginInteractor) as T
        }
    }
}