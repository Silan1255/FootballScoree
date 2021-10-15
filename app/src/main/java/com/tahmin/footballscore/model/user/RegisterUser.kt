package com.tahmin.footballscore.model.user


import com.google.gson.annotations.SerializedName

data class RegisterUserItem(
    @SerializedName("UserMail")
    val userMail: String,
    @SerializedName("UserName")
    val userName: String,
    @SerializedName("UserPassword")
    val userPassword: String,
    @SerializedName("UserUid")
    val userUid: String
)

