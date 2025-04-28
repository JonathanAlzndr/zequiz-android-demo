package com.adopet.quiziz.ui.register

import androidx.lifecycle.ViewModel
import com.adopet.quiziz.data.UserRepository

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun register(username: String, grade: String, password: String) =
        userRepository.register(username, grade, password)
}