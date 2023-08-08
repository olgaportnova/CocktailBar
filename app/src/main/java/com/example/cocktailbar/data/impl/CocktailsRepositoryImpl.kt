package com.example.cocktailbar.data.impl

import android.content.SharedPreferences
import com.example.cocktailbar.data.CocktailsRepository


const val SEARCH_HISTORY = "search_history"
const val COCKTAILS_LIST_KEY = "cocktails_list_key"
const val TRACK_TO_OPEN = "item"

class CocktailsRepositoryImpl(
    private val sharedPref: SharedPreferences
) : CocktailsRepository{
    override fun updateSP(myFavCocktails:String) {
        sharedPref.edit().
        putString(COCKTAILS_LIST_KEY, myFavCocktails)
            .apply()
    }

    override fun getFavString(): String {
        return sharedPref.getString(COCKTAILS_LIST_KEY, null) ?: ""
    }


}