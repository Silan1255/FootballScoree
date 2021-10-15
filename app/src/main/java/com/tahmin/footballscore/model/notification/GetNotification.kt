package com.tahmin.footballscore.model.notification


import com.google.gson.annotations.SerializedName

data class GetNotificationItem(
    @SerializedName("NotifiDate")
    val notifiDate: String,
    @SerializedName("NotifiHeader")
    val notifiHeader: String,
    @SerializedName("NotifiId")
    val notifiId: String,
    @SerializedName("NotifiText")
    val notifiText: String
)
