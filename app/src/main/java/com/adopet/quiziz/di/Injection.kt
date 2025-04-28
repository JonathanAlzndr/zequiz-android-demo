package com.adopet.quiziz.di

import com.adopet.quiziz.data.UserRepository
import com.adopet.quiziz.data.api.ApiConfig

object Injection {
    fun provideRepository(): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}