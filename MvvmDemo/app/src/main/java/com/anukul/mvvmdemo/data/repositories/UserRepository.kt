package com.anukul.mvvmdemo.data.repositories

import com.anukul.mvvmdemo.data.db.AppDatabase
import com.anukul.mvvmdemo.data.db.entities.User
import com.anukul.mvvmdemo.data.network.MyApi
import com.anukul.mvvmdemo.data.network.SafeApiRequest
import com.anukul.mvvmdemo.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun userSignup(
        name: String,
        email: String,
        password: String
    ) : AuthResponse {
        return apiRequest{ api.userSignup(name, email, password)}
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getuser()

}