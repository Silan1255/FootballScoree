package com.example.footballscore.sevis

import com.example.footballscore.model.ResultResponse
import com.example.footballscore.model.maclar.GetMaclar.GetMaclarItem
import com.example.footballscore.model.maclar.SkorTahminEt
import com.example.footballscore.model.user.RegisterUserItem
import com.google.gson.GsonBuilder
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class FutbolAPIServis {

    // http://oyunpuanla.com/futbolSkor/public/index.php/maclar
    //BaseURL -> http://oyunpuanla.com/futbolSkor/public/index.php/

    //futbolcanliskor/liveDataFutbolSkor.php

    private val BASE_URL = "http://oyunpuanla.com/futbolSkor/public/index.php/"
    var gson = GsonBuilder()
        .setLenient()
        .create()
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(FutbolAPI::class.java)

    fun getData(): Single<List<GetMaclarItem>> {
        return api.getFutbol()
    }

    fun setNewUser(body: RegisterUserItem): Single<ResultResponse> {
        return api.newUserRegister(body)
    }

    fun setNewTakim(body: SkorTahminEt): Single<ResultResponse> {
        return api.newSonucRegister(body)
    }


}