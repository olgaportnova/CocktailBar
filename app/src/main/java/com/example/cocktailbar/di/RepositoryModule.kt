package com.example.cocktailbar.di

import com.example.cocktailbar.data.CocktailsRepository
import com.example.cocktailbar.data.impl.CocktailsRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {

//    single<TrackRepository> {
//        TrackRepositoryImpl(get())
//    }



    single<CocktailsRepository> {
        CocktailsRepositoryImpl(get(named(SHARED_PREFS)))
    }

}
