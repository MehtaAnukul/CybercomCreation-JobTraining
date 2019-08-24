package com.anukul.mvvmdemo.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.anukul.mvvmdemo.data.repositories.QuotesRepository
import com.anukul.mvvmdemo.util.lazyDeferred

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }
}
