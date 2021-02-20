package com.devvin.rxjavaapplication

import android.util.Log
import com.devvin.rxjavaapplication.webservice.WebserviceClass
import com.vmcorp.foodisu.model.DogBreed
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class HomeRepository {
    private val TAG = "HomeRespository"
    private val webservice = WebserviceClass.makeRetrofitService()
    private val compositeDisposable = CompositeDisposable()    //when we have more than one observers, we use CompositeDisposable.

    //api call
    fun getDogList() {
        compositeDisposable.add(
            webservice.getDogBreedList()
                .observeOn(AndroidSchedulers.mainThread())  //in which thread we need to handle the result
                .subscribeOn(Schedulers.io())               // in which thread we need to perform the network operation
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<List<DogBreed>> {
        return object : DisposableObserver<List<DogBreed>>() {
            override fun onComplete() {
                Log.i(TAG, "onComplete Invoked")
            }

            override fun onNext(t: List<DogBreed>) {
                Log.i(TAG, "onNext Invoked with value = $t")
            }

            override fun onError(e: Throwable) {
                Log.i(TAG, "onError Invoked with error ${e.message}")
            }
        }
    }

    //all operations should be disposed properly to avoid memory leaks - leads to crash
    fun dispose() {
        compositeDisposable.clear()     //you can still add disposable to the composite disposable
        //or
        compositeDisposable.dispose()   //you will no longer be able to add disposables to that composite disposable.
    }
}