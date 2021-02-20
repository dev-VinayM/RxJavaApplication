package com.devvin.rxjavaapplication

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val repository = HomeRepository()
    fun getDogList() {
        repository.testRxJavaWithDisposableObserver()
    }

    override fun onCleared() {
        super.onCleared()
        repository.dispose()
    }
}