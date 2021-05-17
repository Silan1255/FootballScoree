package com.example.footballscore.model.skor_tahmin


import com.google.gson.annotations.SerializedName

data class SkorTahminEt(
    @SerializedName("UserID")
    val userID: String,
    @SerializedName("Guess")
    val guess: String,
    @SerializedName("MacID")
    val macID: String
)


