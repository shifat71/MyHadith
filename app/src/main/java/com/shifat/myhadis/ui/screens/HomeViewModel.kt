package com.shifat.myhadis.ui.screens

import androidx.lifecycle.ViewModel
import com.shifat.myhadis.model.Hadis
import com.shifat.myhadis.repository.HadisRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HadisRepository
): ViewModel() {

    val isLoading = MutableStateFlow(false)
    val error = MutableStateFlow<String>("")
    val hadisList: StateFlow<List<Hadis>>
        get() = repository.hadis


    init {
        viewModelScope.launch {
            repository.getHadis()
        }
    }

    fun toggleFavorite(hadis: Hadis) {
        repository.toggleFavorite(hadis)
    }

    fun isFavortie(hadis: Hadis): Boolean {
        val currentFavorites = repository.favoriteHadisList.value
        if (currentFavorites.contains(hadis)) {
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