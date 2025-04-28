package com.adopet.quiziz.ui.login

import androidx.lifecycle.ViewModel
import com.adopet.quiziz.data.UserRepository

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun login(username: String, password: String) = userRepository.login(username, password)
}