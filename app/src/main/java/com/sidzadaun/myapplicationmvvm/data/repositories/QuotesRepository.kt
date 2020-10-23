package com.sidzadaun.myapplicationmvvm.data.repositories

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sidzadaun.myapplicationmvvm.data.db.AppDatabase
import com.sidzadaun.myapplicationmvvm.data.db.entities.Quote
import com.sidzadaun.myapplicationmvvm.data.network.MyApi
import com.sidzadaun.myapplicationmvvm.data.network.SafeApiRequest
import com.sidzadaun.myapplicationmvvm.data.preferences.PreferenceProvider
import com.sidzadaun.myapplicationmvvm.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)

private val MINIMUM_INTERVALS = 6

class QuotesRepository(
    private val api: MyApi,
    private val db : AppDatabase,
    private val prefs : PreferenceProvider
) : SafeApiRequest() {
    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>>{
        return withContext(Dispatchers.IO){
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }

    private suspend fun fetchQuotes(){
        val lastSavedAt = prefs.getLastSavedAt()
        if(lastSavedAt==null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))){
            val response = apiRequest { api.getQuotes()}
            quotes.postValue(response.quotes)
        }
    }

    private fun saveQuotes(quotes: List<Quote>){
        Coroutines.io {
            prefs.saveLastSavedAt(LocalDateTime.now().toString())
            db.getQuoteDao().saveAllQuotes(quotes)
        }

    }

    @SuppressLint("NewApi")
    private fun isFetchNeeded(savedAt: LocalDateTime):Boolean{
        return ChronoUnit.HOURS.between(savedAt,LocalDateTime.now()) > MINIMUM_INTERVALS
    }

}