package com.example.footballscore.model.skor


import com.google.gson.annotations.SerializedName

    data class GetUserScoreItem(
        @SerializedName("Point")
        val point: String,
        @SerializedName("UserID")
        val userID: String,
        @SerializedName("UserName")
        val userName: String
    )
