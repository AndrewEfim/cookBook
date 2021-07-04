package com.andreiiefimenko.cookbook.screens.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.lang.ref.WeakReference

class ObserverManager {
    private var lifecycleOwner: WeakReference<LifecycleOwner>? = null //WeakReference
    private val listObservables: MutableList<MutableLiveData<*>> = mutableListOf()

    fun setLifecycleOwner(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = WeakReference(lifecycleOwner)
    }

    fun <T> addObserver(observable: MutableLiveData<T>, observer: Observer<T>) {
        lifecycleOwner?.get()?.let {
            observable.observe(it, observer)
            listObservables.add(observable)
        }
    }

    fun clearObservables() {
        lifecycleOwner?.get()?.let { owner ->
            listObservables.forEach { observer ->
                observer.removeObservers(owner)
            }
        }
    }

    fun clearLifecycleOwner() {
        clearObservables()
        lifecycleOwner?.clear()
    }
}