package com.shifat.myhadis.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import com.shifat.myhadis.model.Hadis
import com.shifat.myhadis.repository.HadisRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.shifat.myhadis.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HadisRepository,
    private val authRepository: AuthRepository,
): ViewModel() {

    val isLoading = MutableStateFlow(false)
    val error = MutableStateFlow<String>("")
    val hadisList: StateFlow<List<Hadis>>
        get() = repository.hadis


    init {
        viewModelScope.launch {
            repository.getHadis()
            repository.getFavoriteHadis(authRepository.userNumber.value)
        }
    }

    fun toggleFavorite(hadis: Hadis) {
        viewModelScope.launch {
            try {
                repository.toggleFavorite(hadis)
                repository.getFavoriteHadis(authRepository.userNumber.value)
                isFavortie(hadis)
            }catch (e: Exception){
                Log.d("FavHadisException", e.toString())
            }
        }
    }

    fun isFavortie(hadis: Hadis): Boolean {
        val currentFavorites = repository.favoriteHadisList.value
        if (currentFavorites.contains(hadis)) {
            Log.d("favvv","haha")
            return true
        }
        return false
    }

    fun sendHadis(hadis: Hadis) {

        viewModelScope.launch {
            try {
                error.value = ""
                isLoading.value = true
               val response = repository.sendHadis(hadis)
                if(response=="SUCCESS"){
                    error.value = "Hadis Sent Successfully"
                }
            }catch (e: Exception){
                error.value = e.message.toString()
            }finally {
                isLoading.value = false
            }
        }
    }


    fun resetError() {
        error.value = ""
    }

}