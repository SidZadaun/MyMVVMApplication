package com.sidzadaun.myapplicationmvvm.data.network.responses

import com.sidzadaun.myapplicationmvvm.data.db.entities.Quote

data class QuotesResponse (
    val isSuccessful : Boolean,
    val quotes : List<Quote>
)