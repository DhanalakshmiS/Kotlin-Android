package com.lifebetweenbraces.telehealerpreproject.demo.view.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.lifebetweenbraces.telehealerpreproject.BR
import com.lifebetweenbraces.telehealerpreproject.R
import com.lifebetweenbraces.telehealerpreproject.base.BaseLifeCycleFragment
import com.lifebetweenbraces.telehealerpreproject.databinding.FragmentListBinding
import com.lifebetweenbraces.telehealerpreproject.demo.Category
import com.lifebetweenbraces.telehealerpreproject.demo.view.adapters.ChipsAdapter
import com.lifebetweenbraces.telehealerpreproject.demo.viewmodel.DemoViewModel
import com.lifebetweenbraces.telehealerpreproject.util.Callback
import com.lifebetweenbraces.telehealerpreproject.util.Constants
import org.jetbrains.anko.toast

/**
 * Created by Dhanalakshmi Srinivasan on 2019-10-19.
 */
class ListFragment(val type:Int=Constants.TYPE_CATEGORY): BaseLifeCycleFragment<FragmentListBinding, DemoViewModel>(){

    companion object {
        var selectedCategory: Category? = null
    }

    var adapter:ChipsAdapter?=null

    override val viewModelClass: Class<DemoViewModel>
        get() = DemoViewModel::class.java

    override val viewModel: DemoViewModel by lazy {
        activity?.let {
            ViewModelProviders.of(it).get(viewModelClass)
        } ?: super.viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(type==Constants.TYPE_CATEGORY) {
            setAdapter()
        }
    }

    override fun getBindingVariable(): Int = BR.listViewModel

    override fun getLayoutId(): Int = R.layout.fragment_list

    fun setAdapter() {
        activity?.let { activity->
            var list:ArrayList<*>? = null
            var clickListener:Callback<Any>?={}
            when(type){
                Constants.TYPE_CATEGORY->{
                    list=viewModel.getCategories()
                    clickListener ={
                        selectedCategory=it as Category
                    }
                }Constants.TYPE_SUB_CATEGORY->{
                    list=viewModel.getSubCategories(selectedCategory)
                    clickListener ={
                        viewModel.addFavourite(it as String)
                        activity.toast("Favourited ${it}")
                    }
                }else->{
                    list=viewModel.favList
                    clickListener ={
                        activity.toast("UnFavourited ${it}")
                        viewModel.deleteFavourite(it as String)
                        setAdapter()
                    }
                }
            }
            if (list != null) {
                adapter?.let {
                    it.items=list
                    it.notifyDataSetChanged()
                } ?:run{
                    val flexboxLayoutManager = FlexboxLayoutManager(activity)
                    flexboxLayoutManager.flexDirection = FlexDirection.ROW
                    viewDataBinding.list.setLayoutManager(flexboxLayoutManager)
                    adapter = ChipsAdapter(activity.application, type)
                    adapter?.items = list
                    viewDataBinding.list.setAdapter(adapter)
                    adapter?.itemClickListener = clickListener
                }
            }
        }
    }
}