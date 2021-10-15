package com.tahmin.footballscore.model.guess

import com.google.gson.annotations.SerializedName

data class UserGuessCheck(
    @SerializedName("userId")
    val userId: String
)