package com.anukul.mvvmdemo.ui.auth

import androidx.lifecycle.LiveData
import com.anukul.mvvmdemo.data.db.entities.User

interface AuthListener {

    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}