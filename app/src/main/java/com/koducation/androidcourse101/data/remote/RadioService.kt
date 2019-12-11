package com.koducation.androidcourse101.data.remote

import com.koducation.androidcourse101.data.remote.model.Radio
import retrofit2.http.GET

interface RadioService {

    @GET("popularRadios.json")
    suspend fun popularRadios(): List<Radio>

    @GET("locationRadios.json")
    suspend fun locationRadios(): List<Radio>
}