package com.tahmin.footballscore.model.maclar

import com.google.gson.annotations.SerializedName

class GetMaclar : ArrayList<GetMaclar.GetMaclarItem>() {
    data class GetMaclarItem(
        @SerializedName("Dakika")
        val dakika: String,
        @SerializedName("FirsTeam")
        val firsTeam: String,
        @SerializedName("LigName")
        val ligName: String,
        @SerializedName("MacID")
        val macID: String,
        @SerializedName("MacSaati")
        val macSaati: String,
        @SerializedName("MacSonucu")
        val macSonucu: String,
        @SerializedName("SecondTeam")
        val secondTeam: String,
        @SerializedName("SkorID")
        val skorID: String,
        @SerializedName("UpdateDateTime")
        val updateDateTime: String
    )
}

