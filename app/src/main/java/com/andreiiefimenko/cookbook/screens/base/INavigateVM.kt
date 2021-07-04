package com.andreiiefimenko.cookbook.screens.base

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections

interface INavigateVM {
    val navDirection: MutableLiveData<NavDirections?>
    val popBack: MutableLiveData<Pair<Int, Boolean>?>

    fun navigate(direction: NavDirections) {
        navDirection.postValue(direction)
    }

    fun popBack() {
        popBack.value = Pair(0, false)
    }

    fun popBack(destination: Int = 0, inclusive: Boolean = false) {
        popBack.value = Pair(destination, inclusive)
    }
}