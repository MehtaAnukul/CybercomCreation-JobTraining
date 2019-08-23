package com.anukul.mvvmdemo.data.network.responses

import com.anukul.mvvmdemo.data.db.entities.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message: String?,
    val user: User?
)