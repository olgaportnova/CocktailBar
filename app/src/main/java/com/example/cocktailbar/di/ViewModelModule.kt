package com.example.cocktailbar.di

import com.example.cocktailbar.presentation.CocktailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {


    viewModel {
        CocktailsViewModel(get())
    }





}