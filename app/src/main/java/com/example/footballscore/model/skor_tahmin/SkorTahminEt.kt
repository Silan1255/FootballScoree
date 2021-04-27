package com.example.footballscore.model.skor_tahmin


import com.google.gson.annotations.SerializedName

data class SkorTahminEt(
 @SerializedName("TahminFirst")
 val tahminFirst: String,
 @SerializedName("TahminSecond")
 val tahminSecond: String
)


