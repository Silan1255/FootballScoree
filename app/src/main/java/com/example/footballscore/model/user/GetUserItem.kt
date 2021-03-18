package com.example.footballscore.model.user


import com.google.gson.annotations.SerializedName

class GetUser : ArrayList<GetUser.GetUserItem>(){
    data class GetUserItem(
        @SerializedName("UserDate")
        val userDate: String,
        @SerializedName("UserID")
        val userID: String,
        @SerializedName("UserMail")
        val userMail: String,
        @SerializedName("UserName")
        val userName: String,
        @SerializedName("UserPassaword")
        val userPassaword: String
    )
}
