
package com.example.footballscore.model.maclar


import com.google.gson.annotations.SerializedName

   data class SkorTahminEt (

    @SerializedName("FirsTeam")
    val firstTeam: String,
    @SerializedName("SecondTeam")
    val secondTeam: String,
    @SerializedName("MacSonucu")
    val macSonucu:String
    )

