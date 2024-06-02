package com.shifat.myhadis.api

import com.shifat.myhadis.model.CheckSubscriptionResponse
import com.shifat.myhadis.model.ConfirmSubscriptionRequest
import com.shifat.myhadis.model.Contact
import com.shifat.myhadis.model.Hadis
import com.shifat.myhadis.model.SubscribeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface HadisApi {

    @GET("hadith")
    suspend fun getHadis(): Response< List<Hadis>>

    @GET("hadith/fav/{mobile}")
    suspend fun getFavoriteHadis(@Path("mobile") mobile: String): Response< List<Hadis> >
    @GET("check_subscription/{mobile}")
    suspend fun isUserSubscribed(@Path("mobile") mobile: String): Response< CheckSubscriptionResponse >
    @POST("subscribe")
    suspend fun subscribeUser(@Body request: SubscribeRequest): Response<SubscribeResponse>

    @POST("confirm_subscription")
    suspend fun confirmSubscription(@Body request: ConfirmSubscriptionRequest): Response<String>

    @GET("favContacts/{mobile}")
    suspend fun getContacts(@Path("mobile") mobile: String): Response< List<Contact> >

    @POST("favContact/{mobile}")
    suspend fun saveContact(@Path("mobile") mobile: String, @Body request: SaveContactRequest ): Response<Contact>


    @DELETE("favContact/{mobile}")
    suspend fun deleteContact(@Path("mobile") mobile: String, @Body request: DeleteContactRequest ): Response<String>

    @PUT("favContact/{mobile}")
    suspend fun updateContact(@Path("mobile") mobile: String, @Body request: UpdateContactRequest ): Response<Contact>

}


data class UpdateContactRequest(
    val mobile: String,
    val favMobile: String,
    val editedFavMobile: String,
    val editedName: String
)
data class DeleteContactRequest(
    val favMobile: String
)

data class SubscribeRequest(
    val number: String
)
data class SaveContactRequest(
    val mobile: String,
    val name: String,
    val favMobile: String
)