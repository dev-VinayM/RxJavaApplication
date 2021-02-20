package com.devvin.rxjavaapplication

import android.util.Log
import com.devvin.rxjavaapplication.webservice.WebserviceClass
import com.vmcorp.foodisu.model.DogBreed
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class HomeRepository {
    private val TAG = "HomeRespository"
    private val webservice = WebserviceClass.makeRetrofitService()
    private val someText = "text"

    private lateinit var observable: Observable<String>     //observe data and emit data to subscribed observer
    private lateinit var observer: Observer<String>     //consume data emitted by observables
    private lateinit var disposable: Disposable         //can be used to terminate the subscription

    private lateinit var disposableObserver: DisposableObserver<String>     //implements both observer and disposable interfaces

    private val compositeDisposable =
        CompositeDisposable()    //when we have more than one observers, we use CompositeDisposable.


    fun testRxJavaWithObserver() {
        observable = Observable.just(someText)

        observer = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                //used to get disposable instance
                disposable = d
                compositeDisposable.add(disposable)
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

        observable.subscribeOn(Schedulers.io())     //thread in which operation needs to be performed
        observable.observeOn(AndroidSchedulers.mainThread())  //thread in which result should be handled
        observable.subscribe(observer)
    }

    fun testRxJavaWithDisposableObserver() {
        /*observable = Observable.just(someText)

        //Note :  DisposableObserver does not need onSubscribe method, as DisposableObserver can dispose by itself.
        disposableObserver = object : DisposableObserver<String>() {
            override fun onComplete() {
                Log.i(TAG, "onComplete Invoked")
            }

            override fun onNext(t: String) {
                Log.i(TAG, "onNext Invoked with value = $t")
            }

            override fun onError(e: Throwable) {
                Log.i(TAG, "onError Invoked")
            }
        }

        observable.subscribeOn(Schedulers.io())     //thread in which operation needs to be performed
        observable.observeOn(AndroidSchedulers.mainThread())  //thread in which result should be handled
        observable.subscribe(disposableObserver)

        compositeDisposable.add(disposableObserver)*/

        //efficient way of coding
        compositeDisposable.add(
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(disposableObserver)
        )
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

    //all operations should be disposed properly to avoid memory leaks - leads to crash
    fun dispose() {
        if (this::disposable.isInitialized) disposable.dispose()

        if (this::disposableObserver.isInitialized) disposableObserver.dispose()

        compositeDisposable.clear()     //you can still add disposable to the composite disposable
        //or
        compositeDisposable.dispose()   //you will no longer be able to add disposables to that composite disposable.
    }
}