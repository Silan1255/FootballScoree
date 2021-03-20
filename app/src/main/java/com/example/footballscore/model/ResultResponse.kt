package com.example.footballscore.model


import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)