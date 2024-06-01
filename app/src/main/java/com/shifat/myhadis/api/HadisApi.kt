package com.shifat.myhadis.api

import com.shifat.myhadis.model.FavoriteHadisRequest
import com.shifat.myhadis.model.Hadis
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface HadisApi {

    @GET("hadith")
    suspend fun getHadis(): Response< List<Hadis>>

    @GET("hadith/fav/{mobile}")
    suspend fun getFavoriteHadis(@Path("mobile") mobile: String): Response< List<Hadis> >

}