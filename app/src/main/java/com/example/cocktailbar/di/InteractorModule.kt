package com.example.cocktailbar.di

import com.example.cocktailbar.domain.CocktailsInteractor
import com.example.cocktailbar.domain.impl.CocktailsInteractorImpl
import com.google.gson.Gson
import org.koin.dsl.module

val interactorModule = module {


    single<CocktailsInteractor> {
        CocktailsInteractorImpl(get())
    }


    factory { Gson() }


}