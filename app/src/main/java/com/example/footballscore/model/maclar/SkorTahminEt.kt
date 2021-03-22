
package com.example.footballscore.model.maclar


import com.google.gson.annotations.SerializedName

   data class SkorTahminEt (
    @SerializedName("TahminFirst")
    val tahminFirst: String,
    @SerializedName("TahminSecond")
    val tahminSecond: String,
    @SerializedName("TahminSonuc")
    val tahminSonuc: String
    )


