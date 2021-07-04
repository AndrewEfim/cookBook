package com.andreiiefimenko.cookbook.screens.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

abstract class ObservableFragment<VM : ObservableViewModel, B : ViewDataBinding> :
    BaseMvvmFragment<VM, B>() {
    protected var navController: NavController? = null
    abstract val fragmentDestinationId: Int
    //protected var logger = LoggerImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  logger.setTag(TAG)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //hideSystemUi(activity)
        navController = NavHostFragment.findNavController(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*viewModel.showDialog.observe(viewLifecycleOwner, Observer { dialog ->
            Logger.log(TAG, "showDialog try to show out")
            if (dialog != null) {
                Logger.log(TAG, "showDialog try to show in")
                dialog.show(parentFragmentManager, dialog.TAG)
                viewModel.showDialog.postValue(null)
            }
        })
        viewModel.showBottomDialog.observe(viewLifecycleOwner, Observer { dialog ->
            if (dialog != null) {
                dialog.show(parentFragmentManager, dialog.TAG)
                viewModel.showBottomDialog.postValue(null)
            }
        })*/
        viewModel

        viewModel.navDirection.observe(viewLifecycleOwner, Observer {
            try {
                if (navController?.currentDestination?.id == fragmentDestinationId && it != null) {
                    viewModel.navDirection.postValue(null)
                    activity?.runOnUiThread { navController?.navigate(it) }
                }
            } catch (e: Throwable) {
            }
        })
        viewModel.popBack.observe(viewLifecycleOwner, Observer { pair ->
            if (pair != null) {
                if (pair.first != 0) {
                    activity?.runOnUiThread { navController?.popBackStack(pair.first, pair.second) }
                } else
                    activity?.runOnUiThread { navController?.popBackStack() }
                viewModel.popBack.postValue(null)
            }
        })
        viewModel.observerManager.setLifecycleOwner(viewLifecycleOwner)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //viewModel.useCases.forEach { it.unsubscribe() }
        viewModel.observerManager.clearLifecycleOwner()
        viewModel.navDirection.removeObservers(viewLifecycleOwner)
        viewModel.popBack.removeObservers(viewLifecycleOwner)
        /*viewModel.showDialog.removeObservers(viewLifecycleOwner)
        viewModel.showDialog.postValue(null)
        viewModel.showBottomDialog.removeObservers(viewLifecycleOwner)
        viewModel.showBottomDialog.postValue(null)*/
    }


}