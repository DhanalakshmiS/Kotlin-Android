package com.lifebetweenbraces.telehealerpreproject.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders

/**
 * Created by Dhanalakshmi Srinivasan on 2019-10-19.
 */

abstract class BaseLifeCycleActivity<B : ViewDataBinding, T : BaseViewModel> : AppCompatActivity() {

    abstract val viewModelClass: Class<T>

    val viewModel: T by lazy { ViewModelProviders.of(this).get(viewModelClass) }

    lateinit var viewDataBinding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDataBinding() {

        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        val result = viewDataBinding.setVariable(getBindingVariable(), viewModel)
        if (!result) {
            throw RuntimeException("ViewModel variable not set. Check the types")
        }
        viewDataBinding.executePendingBindings()
    }

    //  data binding variable
    abstract fun getBindingVariable(): Int

    //  layout id
    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    fun setTitle(title: String) {
        supportActionBar?.title = title
    }
}