package com.anukul.mvvmdemo.ui.home.profile

import androidx.lifecycle.ViewModel
import com.anukul.mvvmdemo.data.repositories.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {

    val user = repository.getUser()
}
