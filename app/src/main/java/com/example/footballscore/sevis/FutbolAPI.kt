package com.example.footballscore.sevis

import com.example.footballscore.model.maclar.GetMaclar.GetMaclarItem
import io.reactivex.Single
import retrofit2.http.GET

interface FutbolAPI {

    //GET , POST
    // http://oyunpuanla.com/futbolSkor/public/index.php/maclar
    //BaseURL -> http://oyunpuanla.com/futbolSkor/public/index.php/

    //liveDataFutbolSkor.php


    @GET("maclar")
    fun getFutbol(): Single<List<GetMaclarItem>>


}