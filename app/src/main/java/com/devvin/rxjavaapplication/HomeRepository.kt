package com.devvin.rxjavaapplication

import android.util.Log
import com.devvin.rxjavaapplication.webservice.WebserviceClass
import com.vmcorp.foodisu.model.DogBreed
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeRepository {
    private val TAG = "HomeRespository"
    private val webservice = WebserviceClass.makeRetrofitService()
    private val someText = "text"

    private lateinit var observable: Observable<String>
    private lateinit var observer: Observer<String>

    fun testRxJava() {
        observable = Observable.just(someText)

        observer = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.i(TAG, "onSubscribed Invoked")
            }
            override fun onNext(s: String) {
                Log.i(TAG, "onNext Invoked with value = $s")
            }
            override fun onError(e: Throwable) {
                Log.i(TAG, "onError Invoked")
            }
            override fun onComplete() {
                Log.i(TAG, "onComplete Invoked")
            }
        }

        observable.subscribe(observer)

    }

    //api call
    fun getDogList() {
        webservice.getDogBreedList()
            .observeOn(AndroidSchedulers.mainThread())  //in which thread we need to handle the result
            .subscribeOn(Schedulers.io())               // in which thread we need to perform the network operation
            .subscribe(this::onSuccess)
    }

    private fun onSuccess(response: List<DogBreed>) {
        Log.i(TAG, "success response : $response")
    }
}