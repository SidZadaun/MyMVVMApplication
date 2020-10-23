package com.sidzadaun.myapplicationmvvm.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.sidzadaun.myapplicationmvvm.data.repositories.QuotesRepository
import com.sidzadaun.myapplicationmvvm.util.lazyDeferred

class QuotesViewModel(repository: QuotesRepository) : ViewModel() {

    val quotes by lazyDeferred { repository.getQuotes() }
}

