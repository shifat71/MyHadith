package com.shifat.myhadis.ui.screens

import androidx.hilt.navigation.compose.hiltViewModel
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
class FavoriteViewModel @Inject constructor(
    private val repository: HadisRepository,
    private val authRepository: AuthRepository
): ViewModel() {

    val userNumber = authRepository.userNumber.value
    val _favoriteHadisList: MutableStateFlow<List<Hadis>> = MutableStateFlow(emptyList())

    val favoriteHadisList: StateFlow<List<Hadis>>
        get() = repository.favoriteHadisList

    init {
        viewModelScope.launch {
            repository.getHadis()
            repository.getFavHadis(userNumber)
        }
    }


    fun toggleFavorite(hadis: Hadis) {
        val currentFavorites = _favoriteHadisList.value.toMutableList()
        if (currentFavorites.contains(hadis)) {
            currentFavorites.remove(hadis)
        } else {
            currentFavorites.add(hadis)
        }
        _favoriteHadisList.value = currentFavorites
    }

}