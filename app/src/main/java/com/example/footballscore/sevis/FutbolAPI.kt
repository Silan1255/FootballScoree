package com.example.footballscore.sevis

import android.telecom.Call
import com.example.footballscore.model.ResultResponse
import com.example.footballscore.model.maclar.GetMaclar.GetMaclarItem
import com.example.footballscore.model.user.RegisterUserItem
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FutbolAPI {
    //GET , POST
    // http://oyunpuanla.com/futbolSkor/public/index.php/maclar
    //BaseURL -> http://oyunpuanla.com/futbolSkor/public/index.php/1 -> path
    //BaseURL -> http://oyunpuanla.com/futbolSkor/public/index.php/macID?=1 -> query
    //BaseURL -> http://oyunpuanla.com/futbolSkor/public/index.php/newUser -> Body
    //liveDataFutbolSkor.php

    companion object {
        private const val MACLAR = "maclar"
        private const val NEW_USER = "newUser"
    }

    @GET(MACLAR)
    fun getFutbol(): Single<List<GetMaclarItem>>

    @POST(NEW_USER)
    fun newUserRegister(@Body body : RegisterUserItem) : Single<ResultResponse>
}