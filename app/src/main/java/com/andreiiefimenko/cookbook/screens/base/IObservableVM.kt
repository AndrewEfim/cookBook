package com.andreiiefimenko.cookbook.screens.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface IObservableVM {
    var observerManager: ObserverManager// = ObserverManager()
    //var useCases: MutableList<UseCase<*>>// = mutableListOf<UseCase<*>>()

    fun <T> addToObservable(observable: MutableLiveData<T>, observer: Observer<T>) {
        observerManager.addObserver(observable, observer)
    }
}