package com.example.footballscore.sevis

import com.example.footballscore.model.ResultResponse
import com.example.footballscore.model.guess.UserGuessCheck
import com.example.footballscore.model.maclar.GetMaclar.GetMaclarItem
import com.example.footballscore.model.notification.GetNotificationItem
import com.example.footballscore.model.skor.GetUserScoreItem
import com.example.footballscore.model.skor_tahmin.GetTahminMaclar
import com.example.footballscore.model.skor_tahmin.SkorSonucItem
import com.example.footballscore.model.skor_tahmin.SkorTahminEt
import com.example.footballscore.model.user.GetUser.GetUserItem
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

    fun getBildiri(): Single<List<GetNotificationItem>> {
        return api.getBildirim()
    }

    fun getSkor(): Single<List<GetUserScoreItem>> {
        return api.getSkor()
    }

    fun getUser(userId : String): Single<List<GetUserItem>> {
        return api.getUser(userId)
    }

    fun  getCanliMac(): Single<List<GetTahminMaclar.GetTahminMaclarItem>>{
        return api.getCanliMaclar()
    }

    fun  getMacGuess(userId: String): Single<List<SkorSonucItem>>{
        return api.getMacGuess(userId)
    }

    fun setNewUser(body: RegisterUserItem): Single<ResultResponse> {
        return api.newUserRegister(body)
    }

    fun setNewTakim(body: SkorTahminEt): Single<ResultResponse> {
        return api.newSonucRegister(body)
    }

    fun chechGuess(userGuessCheck: UserGuessCheck): Single<ResultResponse>{
        return api.checkUserGuess(userGuessCheck)
    }
}