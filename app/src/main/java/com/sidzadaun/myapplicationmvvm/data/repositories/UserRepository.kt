package com.sidzadaun.myapplicationmvvm.data.repositories

import com.sidzadaun.myapplicationmvvm.data.db.AppDatabase
import com.sidzadaun.myapplicationmvvm.data.db.entities.User
import com.sidzadaun.myapplicationmvvm.data.network.MyApi
import com.sidzadaun.myapplicationmvvm.data.network.SafeApiRequest
import com.sidzadaun.myapplicationmvvm.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository(
    private  val api: MyApi,
    private val db: AppDatabase ) : SafeApiRequest() {

    suspend fun userLogin(email:String,password: String) : AuthResponse{
        return apiRequest { api.userLogin(email,password) }
    }

    suspend fun userSignup(
        name: String,
        email: String,
        password: String
    ) : AuthResponse{return apiRequest { api.userSignup(name,email,password) }}

    suspend fun saveUser(user:User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()

}