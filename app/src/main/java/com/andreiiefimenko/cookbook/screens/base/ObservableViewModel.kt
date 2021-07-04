package com.andreiiefimenko.cookbook.screens.base

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections

//abstract class ObservableViewModel : BaseViewModel(), IObservableVM, INavigateVM, IShowingVM {
//    override val navDirection = MutableLiveData<NavDirections?>(null)
//    override val popBack: MutableLiveData<Pair<Int, Boolean>?> =
//        MutableLiveData<Pair<Int, Boolean>?>(null)
//    override val showDialog: MutableLiveData<BaseDialogFragment?> = MutableLiveData(null)
//    override val showBottomDialog: MutableLiveData<BaseBottomDialogFragment?> =
//        MutableLiveData(null)
//
//    override var observerManager: ObserverManager = ObserverManager()
//
//    var useCases: MutableList<UseCase<*>> = mutableListOf<UseCase<*>>()
//
//    override fun onCleared() {
//        super.onCleared()
//        useCases.forEach { it.unsubscribe() }
//        useCases.clear()
//        observerManager.clearLifecycleOwner()
//    }
//}
abstract class ObservableViewModel:BaseViewModel(),INavigateVM,IObservableVM {

    override val navDirection = MutableLiveData<NavDirections?>(null)
    override val popBack: MutableLiveData<Pair<Int, Boolean>?> =
        MutableLiveData<Pair<Int, Boolean>?>(null)

    override var observerManager: ObserverManager = ObserverManager()

    override fun onCleared() {
        super.onCleared()
        observerManager.clearLifecycleOwner()

    }
}