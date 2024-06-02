package com.shifat.myhadis.api

import com.shifat.myhadis.model.CheckSubscriptionResponse
import com.shifat.myhadis.model.Hadis
import com.shifat.myhadis.model.SubscribeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HadisApi {

    @GET("hadith")
    suspend fun getHadis(): Response< List<Hadis>>

    @GET("hadith/fav/{mobile}")
    suspend fun getFavoriteHadis(@Path("mobile") mobile: String): Response< List<Hadis> >
    @GET("check_subscription/{mobile}")
    suspend fun isUserSubscribed(@Path("mobile") mobile: String): Response< CheckSubscriptionResponse >
    @POST("subscribe/{mobile}")
    suspend fun subscribeUser(@Path("mobile") mobile: String): Response<SubscribeResponse>

}