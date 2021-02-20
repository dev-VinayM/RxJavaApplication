package com.devvin.rxjavaapplication.webservice

import com.vmcorp.foodisu.model.DogBreed
import io.reactivex.Observable
import retrofit2.http.GET

interface Webservice {

    @GET("DevTides/DogsApi/master/dogs.json")
    fun getDogBreedList(): Observable<List<DogBreed>>  //for RxJava use "Observable" instead of "Call"
}