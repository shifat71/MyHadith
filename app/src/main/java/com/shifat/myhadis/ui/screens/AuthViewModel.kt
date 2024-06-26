package com.shifat.myhadis.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.shifat.myhadis.model.SubscribeResponse
import com.shifat.myhadis.repository.AuthRepository
import com.shifat.myhadis.repository.HadisRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel@Inject constructor(private val repository: AuthRepository): ViewModel(){


    val userNumber = repository.userNumber
    val referenceNo = MutableStateFlow<String>("")
    val otpError = MutableStateFlow<Boolean>(false)
    val loginError = MutableStateFlow<Boolean>(false)
    val isLoginLoading = MutableStateFlow<Boolean>(false)
    val isOtpLoading = MutableStateFlow<Boolean>(false)
    val isOtpSuccess = MutableStateFlow<Boolean>(false)

    suspend fun subscribeUser(phoneNumber: String): String {
        val response = repository.subscribeUser(phoneNumber)

        userNumber.value = phoneNumber

        if(response.referenceNo != null) {
            Log.d("refNo",response.referenceNo)
            referenceNo.value = response.referenceNo
            return "OTP"
        }
        else if(response.statusCode=="E1351") return "REGISTERED"
        else return "ERROR"
    }

    fun handleOtp(navController: NavHostController, otp: String){
        isOtpLoading.value = true
        viewModelScope.launch {
            try {
                Log.d("otpp", "start")
                val response = repository.confirmSubscription(referenceNo.value, otp)
                Log.d("otpp", response)

                if (response == "SUCCESS") {
                    isOtpSuccess.value = true
                    navController.navigate(Screen.HomeScreen.name)
                } else {
                    otpError.value = true
                }
        }catch (e: Exception){
            Log.e("AuthViewModel", "Error confirming subscription", e)
            otpError.value = true
        }
        finally {
            isOtpLoading.value = false
        }
        }

    }

    fun login(phoneNumber: String, navController: NavHostController){
        isLoginLoading.value = true
        userNumber.value = phoneNumber
        var response = ""
        viewModelScope.launch {

            try {
                   response = subscribeUser(phoneNumber)
                if(response=="OTP") {
                    navController.navigate(Screen.OtpScreen.name)
                }
                else if(response=="REGISTERED") {
                    Log.d("HomeNav","HomeNav Called")
                    navController.navigate(Screen.HomeScreen.name)
                }
                else {
                    loginError.value = true
                }
            } catch (e: Exception) {
                loginError.value = true
                Log.e("AuthViewModel", "Error subscribing user", e)
            }
            finally {
                isLoginLoading.value = false
            }

        }
    }

}