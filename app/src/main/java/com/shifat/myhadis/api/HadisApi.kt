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


    @POST("deleteContact")
    suspend fun deleteContact(@Body request: DeleteContactRequest ): Response<StringResponse>

    @PUT("favContact/{mobile}")
    suspend fun updateContact(@Path("mobile") mobile: String, @Body request: UpdateContactRequest ): Response<Contact>
    @POST("send-sms")
    suspend fun sendHadis(@Body request: SendHadisRequest): Response<String>

    @POST("hadith/fav")
    suspend fun addFavoriteHadith(@Body request: AddFavoriteHadithRequest): Response<String>


    @POST("hadith/fav/delete")
    suspend fun deleteFavoriteHadith(@Body request: AddFavoriteHadithRequest): Response<String>


}


data class StringResponse(
    val message: String
)

data class AddFavoriteHadithRequest(
    val mobile: String,
    val hadithId: Int
)


data class SendHadisRequest(
    val messageBody: String,
    val numbers: List<String>
)

data class UpdateContactRequest(
    val mobile: String,
    val favMobile: String,
    val editedFavMobile: String,
    val editedName: String
)
data class DeleteContactRequest(
    val mobile: String,
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