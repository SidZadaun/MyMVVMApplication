package com.sidzadaun.myapplicationmvvm

import android.app.Application
import com.sidzadaun.myapplicationmvvm.data.db.AppDatabase
import com.sidzadaun.myapplicationmvvm.data.network.MyApi
import com.sidzadaun.myapplicationmvvm.data.network.NetworkConnectionInterceptor
import com.sidzadaun.myapplicationmvvm.data.preferences.PreferenceProvider
import com.sidzadaun.myapplicationmvvm.data.repositories.QuotesRepository
import com.sidzadaun.myapplicationmvvm.data.repositories.UserRepository
import com.sidzadaun.myapplicationmvvm.ui.auth.AuthViewModelFactory
import com.sidzadaun.myapplicationmvvm.ui.home.profile.ProfileViewModelFactory
import com.sidzadaun.myapplicationmvvm.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyAppApplication :Application(),KodeinAware {

    override val kodein =  Kodein.lazy {
        import(androidXModule(this@MyAppApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { QuotesRepository(instance(), instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider{ QuotesViewModelFactory(instance())}


    }

}