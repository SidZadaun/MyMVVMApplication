package com.sidzadaun.myapplicationmvvm.data.network.responses

import com.sidzadaun.myapplicationmvvm.data.db.entities.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message : String?,
    val user : User?
)