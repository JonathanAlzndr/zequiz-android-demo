package com.adopet.quiziz.data.api

import com.adopet.quiziz.data.model.RegisterRequest
import com.adopet.quiziz.data.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("zequiz/auth/register")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

}