package com.adopet.quiziz.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adopet.quiziz.data.api.ApiService
import com.adopet.quiziz.data.model.LoginRequest
import com.adopet.quiziz.data.model.LoginResponse
import com.adopet.quiziz.data.model.RegisterRequest
import com.adopet.quiziz.data.model.RegisterResponse
import com.adopet.quiziz.ui.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository private constructor(
    private val apiService: ApiService
) {

    fun register(username: String, grade: String, password: String): LiveData<Result<RegisterResponse>> {
        val request = RegisterRequest(username, grade, password)
        val result = MutableLiveData<Result<RegisterResponse>>()

        apiService.register(request).enqueue(object: Callback<RegisterResponse>{
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                // Cek jika response berhasil
                if(response.isSuccessful) {
                    if(response.body() != null) {
                        result.value = Result.Success(response.body()!!)
                    } else {
                        result.value = Result.Error("response kosong")
                    }
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                result.value = Result.Error(t.message.toString())
            }
        })

        return result
    }

    fun login(username: String, password: String): MutableLiveData<Result<LoginResponse>> {
        val request = LoginRequest(username, password)
        val result = MutableLiveData<Result<LoginResponse>>()
      /*  apiService.login(request).enqeueu(object: Callback<LoginResponse>{
            override fun onResponse(p0: Call<LoginResponse>, p1: Response<LoginResponse>) {
                //
            }

            override fun onFailure(p0: Call<LoginResponse>, p1: Throwable) {
                //
            }

        })

       */

        return result
    }


    companion object {
        private const val TAG = "UserRepository"

        @Volatile
        private var INSTANCE: UserRepository? = null

        fun getInstance(
            apiService: ApiService,
        ): UserRepository {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = UserRepository(
                        apiService,
                    )
                }
            }
            return INSTANCE as UserRepository
        }
    }

}