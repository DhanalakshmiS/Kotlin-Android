package com.lifebetweenbraces.telehealerpreproject.demo.view.activities

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.lifebetweenbraces.telehealerpreproject.R
import com.lifebetweenbraces.telehealerpreproject.base.BaseViewPagerActivity
import com.lifebetweenbraces.telehealerpreproject.demo.view.fragments.ListFragment
import com.lifebetweenbraces.telehealerpreproject.demo.viewmodel.DemoViewModel
import com.lifebetweenbraces.telehealerpreproject.util.Constants

/**
 * Created by Dhanalakshmi Srinivasan on 2019-10-19.
 */
class DemoViewPagerActivity : BaseViewPagerActivity(){
    override val viewModelClass: Class<DemoViewModel>
        get() = DemoViewModel::class.java

    override fun getFragmentsList(): ArrayList<ListFragment> {
        var fragmentsList = arrayListOf<ListFragment>()
        fragmentsList.add(ListFragment())
        fragmentsList.add(ListFragment(Constants.TYPE_SUB_CATEGORY))
        fragmentsList.add(ListFragment(Constants.TYPE_FAVOURITE))
        return fragmentsList
    }

    override fun getFragmentsTitles(): ArrayList<String> {
        var titlesList = arrayListOf<String>()
        titlesList.add(getString(R.string.categories))
        titlesList.add(getString(R.string.sub_categories))
        titlesList.add(getString(R.string.favourites))
        return titlesList
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.viewpagerMain.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                fragments.get(position)?.setAdapter()
            }

        })
    }
}