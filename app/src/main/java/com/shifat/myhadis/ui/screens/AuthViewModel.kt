package com.shifat.myhadis.ui.screens

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.shifat.myhadis.repository.HadisRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel@Inject constructor(private val repository: HadisRepository): ViewModel(){

    val userNumber = MutableStateFlow<String>("")
    val referenceNo = MutableStateFlow<String>("")
    val loginError = MutableStateFlow<Boolean>(false)

//     fun isUserSubscribed(userId: String): Boolean {
//        return repository.isUserSubscribed(userId)
//    }
//
//     fun subscribeUser(phoneNumber: String): Boolean {
//        userNumber.value = phoneNumber
//        referenceNo.value = repository.subscribeUser(phoneNumber)
//        return referenceNo.value != ""
//    }
//
//     fun login(phoneNumber: String, navController: NavHostController){
//        userNumber.value = phoneNumber
//        if(isUserSubscribed(userNumber.value)){
//            navController.navigate(Screen.HomeScreen.name)
//        }else{
//            if(subscribeUser(phoneNumber)) {
//                navController.navigate(Screen.OtpScreen.name)
//            } else {
//                loginError.value = true
//            }
//        }
//    }

}