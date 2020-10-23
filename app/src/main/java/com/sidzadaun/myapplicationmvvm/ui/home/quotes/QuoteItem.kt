package com.sidzadaun.myapplicationmvvm.ui.home.quotes

import com.sidzadaun.myapplicationmvvm.R
import com.sidzadaun.myapplicationmvvm.data.db.entities.Quote
import com.sidzadaun.myapplicationmvvm.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quote: Quote
) : BindableItem<ItemQuoteBinding>(){

    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }
}