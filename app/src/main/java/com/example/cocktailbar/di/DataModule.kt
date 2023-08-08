package com.example.cocktailbar.di

import android.content.Context
import com.example.cocktailbar.data.CocktailsRepository
import com.example.cocktailbar.data.impl.CocktailsRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val SHARED_PREFS = "fav_cocktails"


val dataModule = module {

    factory (named(SHARED_PREFS)){
        androidContext()
            .getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
    }

    single<CocktailsRepository> {
        CocktailsRepositoryImpl(get())
    }
}