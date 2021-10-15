package com.tahmin.footballscore.model.skor


import com.google.gson.annotations.SerializedName

    data class GetUserScoreItem(
        @SerializedName("Point")
        val point: String,
        @SerializedName("UserUid")
        val userID: String,
        @SerializedName("UserName")
        val userName: String
    )
