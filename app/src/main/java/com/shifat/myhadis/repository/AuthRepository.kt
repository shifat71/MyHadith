package com.shifat.myhadis.repository

import android.util.Log
import com.shifat.myhadis.api.HadisApi
import com.shifat.myhadis.api.SubscribeRequest
import com.shifat.myhadis.model.ConfirmSubscriptionRequest
import com.shifat.myhadis.model.Hadis
import com.shifat.myhadis.model.SubscribeResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AuthRepository @Inject constructor(private val hadisApi: HadisApi){

    val userNumber = MutableStateFlow("01872583391")


    suspend fun subscribeUser(mobileNo: String): SubscribeResponse {

        val response = hadisApi.subscribeUser(SubscribeRequest(mobileNo))


        if(response.isSuccessful && response.body()!=null){
            return response.body()!!
        }
        return SubscribeResponse("404", "404", "404")
    }

    suspend fun confirmSubscription(referenceNo: String, otp: String): String {
        val response = hadisApi.confirmSubscription(
            ConfirmSubscriptionRequest(
                referenceNo=referenceNo,
                otp=otp
            )
        )
        Log.d("RepoErr",response.code().toString() )
        if(response.isSuccessful && response.code()==200 ){
            return "SUCCESS"
        }
        return "ERROR"

    }

}