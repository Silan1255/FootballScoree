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
        private const val BILDIRIM = "notification"
        private const val SKOR = "skor"
        private const val NEW_USER = "newUser"
        private const val NEW_SKOR = "newGuess"
        private const val GET_CANLI_MACLAR = "maclar"
        private const val GET_USER = "getUser/{userId}"
        private const val GET_MAC_GUESS = "guess/{userId}"
        private const val CHECK_GUESS = "checkGuess"
    }

    @GET(MACLAR)
    fun getFutbol(): Single<List<GetMaclarItem>>

    @GET(BILDIRIM)
    fun getBildirim(): Single<List<GetNotificationItem>>

    @GET(SKOR)
    fun getSkor(): Single<List<GetUserScoreItem>>

    @GET(GET_USER)
    fun getUser(@Path("userId") userId: String): Single<List<GetUserItem>>

    @POST(NEW_USER)
    fun newUserRegister(@Body body: RegisterUserItem): Single<ResultResponse>

    @POST(NEW_SKOR)
    fun newSonucRegister(@Body body: SkorTahminEt): Single<ResultResponse>

    @GET(GET_CANLI_MACLAR)
    fun getCanliMaclar(): Single<List<GetTahminMaclar.GetTahminMaclarItem>>

    @GET(GET_MAC_GUESS)
    fun getMacGuess(@Path("userId") userId: String): Single<List<SkorSonucItem>>

    @POST(CHECK_GUESS)
    fun checkUserGuess(@Body userGuessCheck: UserGuessCheck) : Single<ResultResponse>
}