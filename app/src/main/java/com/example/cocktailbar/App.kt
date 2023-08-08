package com.example.cocktailbar

import android.app.Application
import com.example.cocktailbar.di.dataModule
import com.example.cocktailbar.di.interactorModule
import com.example.cocktailbar.di.repositoryModule
import com.example.cocktailbar.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class App (): Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@App)
            modules(dataModule, interactorModule, repositoryModule, viewModelModule)
        }

    }
}