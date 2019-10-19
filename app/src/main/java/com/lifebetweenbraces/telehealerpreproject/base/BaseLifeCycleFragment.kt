package com.lifebetweenbraces.telehealerpreproject.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProviders

/**
 * Created by Dhanalakshmi Srinivasan on 2019-10-19.
 */
abstract class BaseLifeCycleFragment<B : ViewDataBinding, T : AndroidViewModel> : androidx.fragment.app.Fragment() {

    abstract val viewModelClass: Class<T>

    protected open val viewModel: T by lazy {
        ViewModelProviders.of(this).get(viewModelClass)
    }


    lateinit var viewDataBinding: B

    var rootView: View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), null, false)
        rootView = viewDataBinding.root
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
}