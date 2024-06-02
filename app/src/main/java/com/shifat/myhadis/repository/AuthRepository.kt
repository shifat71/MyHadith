package com.shifat.myhadis.repository

import android.util.Log
import com.shifat.myhadis.api.HadisApi
import com.shifat.myhadis.api.SubscribeRequest
import com.shifat.myhadis.model.SubscribeResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(private val hadisApi: HadisApi){


    suspend fun subscribeUser(mobileNo: String): SubscribeResponse {

        val response = hadisApi.subscribeUser(SubscribeRequest(mobileNo))


        if(response.isSuccessful && response.body()!=null){
            return response.body()!!
        }
        return SubscribeResponse("404", "404", "404")
    }

}