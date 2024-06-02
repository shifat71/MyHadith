package com.shifat.myhadis.repository

import android.util.Log
import com.shifat.myhadis.api.HadisApi
import com.shifat.myhadis.model.Hadis
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class HadisRepository @Inject constructor(private val hadisApi: HadisApi){


    private val _hadis = MutableStateFlow<List<Hadis>>(emptyList())
    val hadis:StateFlow< List<Hadis> >
        get() = _hadis


    private val _favoriteHadisList = MutableStateFlow<List<Hadis>>(emptyList())
    val favoriteHadisList:StateFlow< List<Hadis> >
        get() = _favoriteHadisList



    fun toggleFavorite(hadis: Hadis) {
        val currentFavorites = _favoriteHadisList.value.toMutableList()
        if (currentFavorites.contains(hadis)) {
            currentFavorites.remove(hadis)
        } else {
            currentFavorites.add(hadis)
        }
        Log.d("HiShona","Aha")
        _favoriteHadisList.value = currentFavorites
    }




    suspend fun getHadis(){
        val response = hadisApi.getHadis()
        Log.d("favHad", response.body().toString())
        if(response.isSuccessful && response.body()!=null){
            _hadis.emit(response.body()!!)
        }
    }

    suspend fun getFavHadis(mobileNo: String){
        val response = hadisApi.getFavoriteHadis(mobileNo)
        Log.d("favHad", response.body().toString())
        if(response.isSuccessful && response.body()!=null){
            _favoriteHadisList.emit(response.body()!!)
        }
    }

}