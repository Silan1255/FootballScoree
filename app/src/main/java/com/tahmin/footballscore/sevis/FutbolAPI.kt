package com.tahmin.footballscore.sevis

import com.tahmin.footballscore.model.ResultResponse
import com.tahmin.footballscore.model.guess.UserGuessCheck
import com.tahmin.footballscore.model.maclar.GetMaclar.GetMaclarItem
import com.tahmin.footballscore.model.notification.GetNotificationItem
import com.tahmin.footballscore.model.skor.GetUserScoreItem
import com.tahmin.footballscore.model.skor_tahmin.GetTahminMaclar
import com.tahmin.footballscore.model.skor_tahmin.SkorSonucItem
import com.tahmin.footballscore.model.skor_tahmin.ScoreGuess
import com.tahmin.footballscore.model.user.GetUser.GetUserItem
import com.tahmin.footballscore.model.user.RegisterUserItem
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FutbolAPI {
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
    fun newSonucRegister(@Body body: ScoreGuess): Single<ResultResponse>

    @GET(GET_CANLI_MACLAR)
    fun getCanliMaclar(): Single<List<GetTahminMaclar.GetTahminMaclarItem>>

    @GET(GET_MAC_GUESS)
    fun getMacGuess(@Path("userId") userId: String): Single<List<SkorSonucItem>>

    @POST(CHECK_GUESS)
    fun checkUserGuess(@Body userGuessCheck: UserGuessCheck) : Single<ResultResponse>
}