/*
 * Copyright 2019 Eric Kogi. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
