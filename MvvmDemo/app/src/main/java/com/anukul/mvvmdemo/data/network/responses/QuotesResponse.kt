package com.anukul.mvvmdemo.data.network.responses

import com.anukul.mvvmdemo.data.db.entities.Quote

data class QuotesResponse (
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)