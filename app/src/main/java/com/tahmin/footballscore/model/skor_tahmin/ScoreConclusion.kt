package com.tahmin.footballscore.model.skor_tahmin


import com.google.gson.annotations.SerializedName

    data class SkorSonucItem(
        @SerializedName("Dakika")
        val dakika: String,
        @SerializedName("FirsTeam")
        val firsTeam: String,
        @SerializedName("Guess")
        val guess: String,
        @SerializedName("LigImage")
        val ligImage: String,
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
        val updateDateTime: String,
        @SerializedName("UserDate")
        val userDate: String,
        @SerializedName("UserGuessDateTime")
        val userGuessDateTime: String,
        @SerializedName("UserGuessID")
        val userGuessID: String,
        @SerializedName("UserID")
        val userID: String,
        @SerializedName("UserMail")
        val userMail: String,
        @SerializedName("UserName")
        val userName: String,
        @SerializedName("UserPassword")
        val userPassword: String,
        @SerializedName("UserUid")
        val userUid: String
    )
