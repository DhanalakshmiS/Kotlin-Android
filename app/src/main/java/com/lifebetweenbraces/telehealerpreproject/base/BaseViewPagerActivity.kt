package com.lifebetweenbraces.telehealerpreproject.base

import android.os.Bundle
import com.lifebetweenbraces.telehealerpreproject.BR
import com.lifebetweenbraces.telehealerpreproject.R
import com.lifebetweenbraces.telehealerpreproject.databinding.ActivityDemoViewPagerBinding
import com.lifebetweenbraces.telehealerpreproject.demo.viewmodel.DemoViewModel
import com.lifebetweenbraces.telehealerpreproject.demo.view.fragments.ListFragment

/**
 * Created by Dhanalakshmi Srinivasan on 2019-10-19.
 */
abstract class BaseViewPagerActivity : BaseLifeCycleActivity<ActivityDemoViewPagerBinding, DemoViewModel>() {

    override fun getBindingVariable(): Int = BR.demoViewModel

    override fun getLayoutId(): Int = R.layout.activity_demo_view_pager

    lateinit var fragments: ArrayList<ListFragment>
    lateinit var titles: ArrayList<String>

    abstract fun getFragmentsList(): ArrayList<ListFragment>
    abstract fun getFragmentsTitles(): ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPagerAdapter()
    }

    fun setPagerAdapter() {
        fragments = getFragmentsList()
        titles = getFragmentsTitles()
        if (fragments.size == titles.size) {

            supportFragmentManager.let {
                viewDataBinding.viewpagerMain.adapter = BaseViewPagerAdapter(it, fragments, titles)
                viewDataBinding.tabsMain.setupWithViewPager(viewDataBinding.viewpagerMain)
                viewDataBinding.viewpagerMain.offscreenPageLimit = 2
            }
        }

    }
}