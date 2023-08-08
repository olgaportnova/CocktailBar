package com.example.cocktailbar.domain

import com.example.cocktailbar.domain.model.Cocktail


interface CocktailsInteractor {


    fun addCocktailsToFav(cocktail: Cocktail)

    fun getFavString(): String?

    fun updateFavList(updatedFavList :ArrayList<Cocktail>) :Unit

    fun getFavList() : ArrayList<Cocktail>

}