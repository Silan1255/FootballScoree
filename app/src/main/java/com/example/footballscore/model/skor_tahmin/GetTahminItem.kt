package com.example.footballscore.model.skor_tahmin

import com.google.gson.annotations.SerializedName

class GetTahminMaclar : ArrayList<GetTahminMaclar.GetTahminMaclarItem>() {
    data class GetTahminMaclarItem(
        @SerializedName("Dakika")
        val tahminDakika: String,
        @SerializedName("FirsTeam")
        val tahminFirsTeam: String,
        @SerializedName("LigName")
        val tahminLigName: String,
        @SerializedName("MacID")
        val tahminMacID: String,
        @SerializedName("MacSaati")
        val tahminMacSaati: String,
        @SerializedName("MacSonucu")
        val tahminMacSonucu: String,
        @SerializedName("SecondTeam")
        val tahminSecondTeam: String,
        @SerializedName("SkorID")
        val tahminSkorID: String,
        @SerializedName("UpdateDateTime")
        val tahminUpdateDateTime: String,
        @SerializedName("LigImage")
        val tahminImage: String

    )
}

