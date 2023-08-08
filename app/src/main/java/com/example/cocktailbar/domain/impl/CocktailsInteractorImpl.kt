package com.example.cocktailbar.domain.impl

import com.example.cocktailbar.data.CocktailsRepository
import com.example.cocktailbar.domain.CocktailsInteractor
import com.example.cocktailbar.domain.model.Cocktail
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap


const val INDEX_FOR_NEW_COCKTAIL = 0


class CocktailsInteractorImpl (private val cocktailRepository: CocktailsRepository) : CocktailsInteractor{


    override fun getFavString(): String {
        var favString = cocktailRepository.getFavString()
        return if (favString !=null) {
            favString
        } else {
            ""
        }
    }


    override fun getFavList() : ArrayList<Cocktail> {
        if (getFavString().isNotEmpty()) {
            var a = createFavListFromJson(getFavString())


            return a
        }
        else {
            return arrayListOf<Cocktail>()
        }

    }


    override fun addCocktailsToFav(cocktail: Cocktail) {
        var currentFavString = getFavString()
        if (currentFavString.isNotEmpty()) {
            var currentFavArrayList = currentFavString?.let { createFavListFromJson(it) }
            var currentHistoryArray = createFavList1FromJson(currentFavString) // создаем из строки список треков array
            if (currentFavArrayList?.size !== 0) { // если история поиска не пустая
                updateFavList(currentFavArrayList!!)
            }
        }
        else {
            var currentFavArrayList: ArrayList<Cocktail> = arrayListOf() // создаем пустой список истории
            currentFavArrayList.add(INDEX_FOR_NEW_COCKTAIL, cocktail)
            updateFavList(currentFavArrayList)
        }
    }

    override fun updateFavList(updatedFav: ArrayList<Cocktail>) {
        val updatedFavJson = createJsonFromFavList(updatedFav)
        cocktailRepository.updateSP(updatedFavJson)
    }




    fun createFavListFromJson(json: String): ArrayList<Cocktail> {
        var a = Gson().fromJson(json, ArrayList<LinkedTreeMap<String, Any>>()::class.java)
        var b = ArrayList<Cocktail>()
        a.forEach { aa ->
            b.add(
                Cocktail(
                    aa.get("image") as String,
                    aa.get("name") as String,
                    aa.get("discrip") as String,
                    aa.get("recep") as String,
                    aa.get("ing") as List<String>
                )
            )
        }

        return b
    }

    fun createFavList1FromJson(json: String): Array<Cocktail> {
        return Gson().fromJson(json, Array<Cocktail>::class.java)
    }

    fun createJsonFromFavList(trackList: ArrayList<Cocktail>): String {
        return Gson().toJson(trackList)
    }





}



