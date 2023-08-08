package com.example.cocktailbar.presentation

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktailbar.domain.CocktailsInteractor
import com.example.cocktailbar.domain.model.Cocktail
import com.example.cocktailbar.domain.model.State

class CocktailsViewModel(
    private val cocktaiInteractor: CocktailsInteractor,

) : ViewModel() {


    private var favListStatusLiveData = MutableLiveData<State>()

    fun getFavListStatusLiveData(): LiveData<State> = favListStatusLiveData


    private var cocktails = ArrayList<Cocktail>()
    private val handler = Handler(Looper.getMainLooper())


    private val searchRunnable = Runnable {
            getFavList()
            showFavList()
    }

    fun onDestroy() {
        handler.removeCallbacks(searchRunnable)
    }

    fun onResume() {
        getFavList()
        if (cocktails.isEmpty()){
            showFavList()
        }
    }

    fun getFavList():ArrayList<Cocktail> {
        return  cocktaiInteractor.getFavList()
    }

    fun showFavList() {
        favListStatusLiveData.postValue(
            State(
                favList = getFavList(),
            )
        )
    }

    fun addNewCocktailToFav(cocktail: Cocktail) {
        cocktaiInteractor.addCocktailsToFav(cocktail)
    }
//
    fun openDetails(cocktail: Cocktail) {
     //   internalNavigationInteractor.openTrack(track)
    }



}



