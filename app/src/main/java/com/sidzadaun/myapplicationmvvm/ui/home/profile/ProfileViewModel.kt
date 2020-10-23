package com.sidzadaun.myapplicationmvvm.ui.home.profile

import androidx.lifecycle.ViewModel
import com.sidzadaun.myapplicationmvvm.data.repositories.UserRepository

class ProfileViewModel(repository: UserRepository) : ViewModel() {

    val user = repository.getUser()

}