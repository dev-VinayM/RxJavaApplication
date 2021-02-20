package com.devvin.rxjavaapplication

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val repository = HomeRepository()
    private val rxJavaConcepts = RxJavaConcepts()

    fun getDogList() {
//        rxJavaConcepts.testRxJavaWithDisposableObserver()
        repository.getDogList()
    }

    override fun onCleared() {
        super.onCleared()
        repository.dispose()
    }
}