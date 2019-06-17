package com.kogicodes.pawametinderforcats.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.kogicodes.pawametinderforcats.data.ActionState
import com.kogicodes.pawametinderforcats.data.CatRepository
import com.kogicodes.pawametinderforcats.model.Cat
import com.kogicodes.pawametinderforcats.model.custom.Resource

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var catRepository: CatRepository = CatRepository(application)
    private val listingObservable = MediatorLiveData<Resource<List<Cat>>>()

    init {
        
        listingObservable.addSource(catRepository.listingObservable) { data ->
            listingObservable.setValue(
                data
            )
        }

    }


    fun observeListing(): LiveData<Resource<List<Cat>>> {
        return listingObservable
    }

    fun getCat(catId: String): LiveData<Cat>{
       return catRepository.getCat(catId)
    }


    fun listings() {
        catRepository.getListings()
    }

    fun setIsFavorite(cat: Cat, b: Boolean) {

        cat.isFavorite=b
        catRepository.updateCat(cat)
    }

    fun like(cat: Cat) {

        cat.actionState=ActionState.LIKED
        catRepository.updateCat(cat)
    }

    fun dislike(cat: Cat) {

        cat.actionState=ActionState.DISLIKED
        catRepository.updateCat(cat)
    }

    fun getFavorites() : LiveData<List<Cat>> {

        return catRepository.getFavorites()
    }

}
