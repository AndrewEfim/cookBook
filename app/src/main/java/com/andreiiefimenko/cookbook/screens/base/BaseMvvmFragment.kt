package com.andreiiefimenko.cookbook.screens.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder

abstract class BaseMvvmFragment<VM : BaseViewModel, B : ViewDataBinding> :BaseFragment(){

    inline fun <VMType : ViewModel> viewModelFactory(crossinline f: () -> VMType) =
        object : ViewModelProvider.Factory {
            override fun <VM : ViewModel> create(aClass: Class<VM>) =
                f() as VM
        }

    protected lateinit var viewModel: VM
    protected lateinit var binding: B

    abstract fun provideViewModel(): VM
    abstract fun provideSwitchableViews(): List<View>
    abstract fun provideLayoutId(): Int
    abstract fun provideLifecycleOwner(): BaseMvvmFragment<VM, B>
    open fun setLoggedIn(): Boolean = true

    companion object {
        const val SUM_SUB_ACTIVITY_REQUEST_CODE: Int = 100
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, provideLayoutId(), container, false)
        viewModel = provideViewModel()
        //viewModel.logger.setTag(viewModel.TAG)
        //viewModel.isLoggedIn = setLoggedIn()

       /* viewModel.alertDialogBuilder = MaterialAlertDialogBuilder(
            requireContext(),
            R.style.CustomMaterialDialog
        ).setCancelable(false)*/
        binding.setVariable(BR._all,viewModel)

        //binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = provideLifecycleOwner()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.switchableViewsList = { provideSwitchableViews() }
    }



    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onResume() {
        super.onResume()
        //hideKeyboardAndNavigation(activity)
    }

    override fun onPause() {
        super.onPause()
        //hideKeyboardAndNavigation(activity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
  //      viewModel.clear()
//        viewModel.logOut.removeObservers(viewLifecycleOwner)
    }

    private fun hideSystemUi(activity: Activity?) {
        activity?.onWindowFocusChanged(true)
    }

 /*   protected open fun hideKeyboardAndNavigation(activity: Activity?) {
        defocusAndHideKeyboard(activity)
        //hideSystemUi(activity)
    }

    fun showMessage(text: String, duration: Int = Toast.LENGTH_SHORT) {
        Logger.logError(TAG, text)
        Logger.showMessage(text, duration)
    }

    fun showMessage(textId: Int, duration: Int = Toast.LENGTH_SHORT) {
        Logger.logError(TAG, App.appContext.resources.getString(textId))
        Logger.showMessage(textId, duration)
    }*/

}