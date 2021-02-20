package com.devvin.rxjavaapplication

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val repository = HomeRepository()
    fun getDogList() {
        repository.testRxJava()
    }
}