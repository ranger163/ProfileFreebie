package me.inassar.profilefreebie.state

sealed class ScreenState<out T> {
    object Loading : ScreenState<Nothing>()
    class Render<T>(val renderState: T) : ScreenState<T>()
}