package me.inassar.profilefreebie.view.activity.login

import me.inassar.profilefreebie.postDelayed

class LoginInteractor {

    interface onLoginFinishedListener {
        fun onEmailError()
        fun onPasswordError()
        fun onSuccess()
    }

    fun login(email: String, password: String, listener: onLoginFinishedListener) {
        // Mocking login, I'm creating a handler to delay the response couple seconds
        postDelayed(2000) {
            when {
                email.isEmpty() -> listener.onEmailError()
                password.isEmpty() -> listener.onPasswordError()
                else -> listener.onSuccess()
            }
        }
    }

}