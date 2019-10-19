package com.lifebetweenbraces.telehealerpreproject.demo.view.activities

import android.os.Bundle
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.lifebetweenbraces.telehealerpreproject.BR
import com.lifebetweenbraces.telehealerpreproject.R
import com.lifebetweenbraces.telehealerpreproject.base.BaseLifeCycleActivity
import com.lifebetweenbraces.telehealerpreproject.databinding.ActivityDemoBinding
import com.lifebetweenbraces.telehealerpreproject.demo.Category
import com.lifebetweenbraces.telehealerpreproject.demo.view.adapters.ChipsAdapter
import com.lifebetweenbraces.telehealerpreproject.demo.viewmodel.DemoViewModel
import com.lifebetweenbraces.telehealerpreproject.util.Constants

/**
 * Created by Dhanalakshmi Srinivasan on 2019-10-19.
 */

//Used this activity earlier to display all three lists in same activity
class DemoActivity : BaseLifeCycleActivity<ActivityDemoBinding, DemoViewModel>() {

    var subCategoriesAdapter: ChipsAdapter? = null
    var favouritesAdapter: ChipsAdapter? = null

    var selectedCategory: String = ""

    var favList: ArrayList<String> = ArrayList()

    override val viewModelClass: Class<DemoViewModel>
        get() = DemoViewModel::class.java

    override fun getBindingVariable(): Int = BR.demoViewModel

    override fun getLayoutId(): Int = R.layout.activity_demo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCategoriesAdapter()
    }

    fun setCategoriesAdapter() {
        var list = viewModel.getCategories()
        if (list != null && list.size > 0) {
            val flexboxLayoutManager = FlexboxLayoutManager(this)
            flexboxLayoutManager.flexDirection = FlexDirection.ROW
            viewDataBinding.listCategories.setLayoutManager(flexboxLayoutManager)
            val adapter = ChipsAdapter(application, Constants.TYPE_CATEGORY)
            adapter.items = list
            viewDataBinding.listCategories.setAdapter(adapter)
            adapter.itemClickListener = {
                setSubCategoriesAdapter(it as Category)
            }
        }
    }

    fun setSubCategoriesAdapter(category: Category) {
        var list = viewModel.getSubCategories(category)
        if (list != null && list.size > 0) {
            subCategoriesAdapter?.let {
                it.items = list
                it.notifyDataSetChanged()
            } ?: run {
                val flexboxLayoutManager = FlexboxLayoutManager(this)
                flexboxLayoutManager.flexDirection = FlexDirection.ROW
                viewDataBinding.listSubCategories.setLayoutManager(flexboxLayoutManager)
                subCategoriesAdapter = ChipsAdapter(application, Constants.TYPE_SUB_CATEGORY)
                subCategoriesAdapter?.items = list
                viewDataBinding.listSubCategories.setAdapter(subCategoriesAdapter)
                subCategoriesAdapter?.itemClickListener = {
                    setFavouritesAdapter(it as String)
                }
            }
        }
    }

    fun setFavouritesAdapter(item:String) {
        viewModel.addFavourite(item)
        var list=viewModel.favList
        if (list != null && list.size > 0) {
            favouritesAdapter?.let {
                it.items = list
                it.notifyDataSetChanged()
            } ?: run {
                val flexboxLayoutManager = FlexboxLayoutManager(this)
                flexboxLayoutManager.flexDirection = FlexDirection.ROW
                viewDataBinding.listFavourites.setLayoutManager(flexboxLayoutManager)
                favouritesAdapter = ChipsAdapter(application, Constants.TYPE_FAVOURITE)
                favouritesAdapter?.items = list
                viewDataBinding.listFavourites.setAdapter(favouritesAdapter)
                favouritesAdapter?.itemClickListener = {
                    notifyFavouritesAdapter(it as String)
                }
            }
        }
    }

    fun notifyFavouritesAdapter(item:String){
        viewModel.deleteFavourite(item)
        favouritesAdapter?.let {
            it.items = viewModel.favList
            it.notifyDataSetChanged()
        }
    }
}