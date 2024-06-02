package com.shifat.myhadis.model


data class SubscribeResponse(
    val statusCode: String,
    val referenceNo: String,
    val statusDetail: String
)


data class CheckSubscriptionResponse(
    val statusCode: String,
    val statusDetail: String,
    val subscriptionStatus: String
)
