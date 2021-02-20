package com.devvin.rxjavaapplication.webservice

import com.devvin.rxjavaapplication.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object WebserviceClass {

    fun makeRetrofitService(): Webservice {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())      //rxJava : add this line
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Webservice::class.java)
    }
}