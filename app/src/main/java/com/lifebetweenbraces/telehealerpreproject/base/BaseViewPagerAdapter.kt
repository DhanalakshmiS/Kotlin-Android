package com.lifebetweenbraces.telehealerpreproject.base

import com.lifebetweenbraces.telehealerpreproject.demo.view.fragments.ListFragment

/**
 * Created by Dhanalakshmi Srinivasan on 2019-10-19.
 */
class BaseViewPagerAdapter : androidx.fragment.app.FragmentPagerAdapter {

    var fragmentList = arrayListOf<ListFragment>()
    var pageTitleList = arrayListOf<String>()

    constructor(fragmentManager: androidx.fragment.app.FragmentManager) : super(fragmentManager)
    constructor(fragmentManager: androidx.fragment.app.FragmentManager, pagerFragmentsList: ArrayList<ListFragment>) : this(fragmentManager) {
        this.fragmentList = pagerFragmentsList

    }
    constructor(fragmentManager: androidx.fragment.app.FragmentManager, pagerFragmentsList: ArrayList<ListFragment>, titleList: ArrayList<String>) : this(fragmentManager) {
        this.fragmentList = pagerFragmentsList
        this.pageTitleList = titleList
    }

    override fun getItem(position: Int) = fragmentList[position]

    override fun getCount() = fragmentList.size

    override fun getPageTitle(position: Int) = pageTitleList[position]
}