package com.kogicodes.pawametinderforcats.data

import NetworkUtils
import RequestService
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev.cabinzz.utils.ErrorUtils
import com.google.gson.Gson
import com.kogicodes.pawametinderforcats.R
import com.kogicodes.pawametinderforcats.data.dao.CatDao
import com.kogicodes.pawametinderforcats.model.Cat
import com.kogicodes.pawametinderforcats.model.custom.Resource
import com.kogicodes.pawametinderforcats.ui.uiUtils.AppiException
import com.kogicodes.pawametinderforcats.ui.uiUtils.FailureUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CatRepository(application: Application) {
    private val catDao: CatDao

    private val db: AppDatabase = AppDatabase.getDatabase(application)!!
    val listingObservable = MutableLiveData<Resource<List<Cat>>>()


    private val context: Context

    init {
        catDao = db.catDao()
        context = application.applicationContext
    }

    fun getListings() {
        setIsLoading(Observable.LISTINGS)
        if (NetworkUtils.isConnected(context)) {
            excuteGetCats()
        } else {
            setNetworkError(Observable.LISTINGS)
        }
    }


    private fun excuteGetCats() {
        Log.d("CallOnStart", Gson().toJson(""))
        GlobalScope.launch(context = Dispatchers.Main) {
            val call = RequestService.getService().getCats(0,100)
            call.enqueue(object : Callback<List<Cat>> {
                override fun onFailure(call: Call<List<Cat>>?, t: Throwable?) {
                    onFailure(Observable.LISTINGS, t, FailureUtils().parseError(call, t))
                }

                override fun onResponse(call: Call<List<Cat>>?, response: Response<List<Cat>>?) {
                    onResponse(response, Observable.LISTINGS)
                }
            })
        }
    }

   

    private fun setNetworkError(observable: Observable) {
        setIsError(
            observable, context.getString(R.string.network_issue_message),
            AppiException(context.getString(R.string.network_issue_message))
        )
    }

    private fun onFailure(observable: Observable, t: Throwable?, agriException: AppiException) {
        setIsError(observable, t.toString(), agriException)
    }

    private fun onResponse(response: Response<List<Cat>>?, observable: Observable) {
        if (response != null) {
            if (response.isSuccessful) {

                    setIsSuccesful(observable, response.body()!!)


            } else {
                setIsError(observable, "", ErrorUtils().parseError(response))
            }
        } else {
            setIsError(observable, "", AppiException("Error Loading Data"))
        }
    }


    private fun setIsLoading(observable: Observable) {
        when (observable) {
            Observable.LISTINGS -> listingObservable.postValue(Resource.loading(null))
        }
    }

    private fun <T> setIsSuccesful(observable: Observable, data: T?) {
        when (observable) {
            Observable.LISTINGS -> listingObservable.postValue(Resource.success( formatData(data as List<Cat>)))
        }

    }

    private fun formatData(list: List<Cat>): List<Cat> {

        for(cat in   list){
            catDao.insert(cat)

        }

        return list
    }

    private fun setIsError(observable: Observable, message: String, exception: AppiException) {
        when (observable) {
            Observable.LISTINGS -> listingObservable.postValue(Resource.error(message, null, exception))
        }
    }

    fun getCat(catId: String): LiveData<Cat> {

        return catDao.getWithId(catId)
    }

    fun updateCat(cat: Cat) {
        catDao.update(cat)

    }

    fun getFavorites(): LiveData<List<Cat>> {

        return catDao.getAllFavorites()
    }

    enum class Observable {
        LISTINGS,
        SEARCHLISTINGS

    }




}
