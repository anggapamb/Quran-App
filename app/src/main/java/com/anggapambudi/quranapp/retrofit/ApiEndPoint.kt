package com.anggapambudi.quranapp.retrofit

import com.anggapambudi.quranapp.model.AllSuratResponse
import com.anggapambudi.quranapp.model.GetDetailSuratResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEndPoint {

    @GET("/surah")
    fun getAllSurat(): Call<AllSuratResponse>

    @GET("/surah/{number}")
    fun getDetailSurat(
            @Path("number") number: String
    ): Call<GetDetailSuratResponse>

}