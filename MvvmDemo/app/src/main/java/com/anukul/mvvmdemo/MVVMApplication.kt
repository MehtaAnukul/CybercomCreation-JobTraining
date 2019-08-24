package com.anukul.mvvmdemo

import android.app.Application
import com.anukul.mvvmdemo.data.db.AppDatabase
import com.anukul.mvvmdemo.data.network.MyApi
import com.anukul.mvvmdemo.data.network.NetworkConnectionInterceptor
import com.anukul.mvvmdemo.data.preferences.PreferenceProvider
import com.anukul.mvvmdemo.data.repositories.QuotesRepository
import com.anukul.mvvmdemo.data.repositories.UserRepository
import com.anukul.mvvmdemo.ui.auth.AuthViewModelFactory
import com.anukul.mvvmdemo.ui.home.profile.ProfileViewModelFactory
import com.anukul.mvvmdemo.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { QuotesRepository(instance(), instance(),instance())}
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider{ QuotesViewModelFactory(instance()) }


    }
}