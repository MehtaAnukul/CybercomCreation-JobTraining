package com.anukul.mvvmdemo.ui.home.quotes

import com.anukul.mvvmdemo.R
import com.anukul.mvvmdemo.data.db.entities.Quote
import com.anukul.mvvmdemo.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quote: Quote
) : BindableItem<ItemQuoteBinding>(){

    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }


}