package com.koducation.androidcourse101.data.remote

import com.koducation.androidcourse101.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RadioServiceProvider @Inject constructor() {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.SERVER_URL)
        .build()

    val radioService = retrofit.create<RadioService>(RadioService::class.java)

}