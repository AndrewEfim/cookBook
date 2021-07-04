package com.andreiiefimenko.cookbook.screens.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseMvvmActivity<VM : BaseViewModel, B : ViewDataBinding>:BaseActivity() {

    inline fun <TViewModel : ViewModel> viewModelFactory(crossinline f: () -> TViewModel) =
        object : ViewModelProvider.Factory {
            override fun <VM : ViewModel> create(aClass: Class<VM>) =
                f() as VM
        }

    protected lateinit var viewModel: VM
    protected lateinit var binding: B

    abstract fun provideViewModel(): VM

    abstract fun provideLayoutId(): Int

    abstract fun provideLifecycleOwner(): BaseMvvmActivity<VM, B>

    open fun setLoggedIn(): Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()

        binding = DataBindingUtil.setContentView(this, provideLayoutId())
        //binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = provideLifecycleOwner()
    }
}