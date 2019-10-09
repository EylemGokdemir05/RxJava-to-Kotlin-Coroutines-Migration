package com.koducation.androidcourse101.data

import com.koducation.androidcourse101.data.model.Radio
import io.reactivex.Single
import retrofit2.http.GET

interface RadioService {

    @GET("popularRadios.json")
    fun popularRadios(): Single<List<Radio>>

    @GET("locationRadios.json")
    fun locationRadios(): Single<List<Radio>>
}