package com.example.cocktailbar.data

interface CocktailsRepository {

    fun updateSP(myFavCocktails: String)
    fun getFavString() : String


}