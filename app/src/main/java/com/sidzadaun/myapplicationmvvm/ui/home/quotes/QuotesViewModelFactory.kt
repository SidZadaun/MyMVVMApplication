package com.sidzadaun.myapplicationmvvm.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sidzadaun.myapplicationmvvm.data.repositories.QuotesRepository
import com.sidzadaun.myapplicationmvvm.data.repositories.UserRepository
@Suppress("UNCHECKED_CAST")

class QuotesViewModelFactory(
    private  val repository : QuotesRepository):ViewModelProvider.NewInstanceFactory(){

    override fun <T: ViewModel?> create(modelClass: Class<T>):T{
        return QuotesViewModel(repository) as T
    }

}