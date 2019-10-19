package com.lifebetweenbraces.telehealerpreproject.demo.viewmodel

import android.app.Application
import com.lifebetweenbraces.telehealerpreproject.base.BaseViewModel
import com.lifebetweenbraces.telehealerpreproject.demo.Category
import com.lifebetweenbraces.telehealerpreproject.demo.Data

/**
 * Created by Dhanalakshmi Srinivasan on 2019-10-19.
 */
class DemoViewModel(app: Application) : BaseViewModel(app) {

    lateinit var dummyData: Data
    lateinit var favList:ArrayList<String>

    init {
        initDummyData()
    }

    fun initDummyData() {
        var categories = ArrayList<Category>()
        var fruits = ArrayList<String>()
        var cars = ArrayList<String>()
        fruits.add("Apple")
        fruits.add("Orange")
        fruits.add("Mango")
        fruits.add("Pine apple")
        fruits.add("Pomegranate")
        fruits.add("Cherry")
        fruits.add("Grapes")
        fruits.add("Bannana")
        fruits.add("Avacado")
        fruits.add("Mosambi")
        cars.add("Benz")
        cars.add("BMW")
        cars.add("Ferrari")
        cars.add("Lamborgini")
        cars.add("Jaquar")
        cars.add("Hyundai")
        cars.add("Honda")
        cars.add("Toyota")
        categories.add(Category(title = "Fruits", values = fruits))
        categories.add(Category(title = "Fruits1", values = fruits))
        categories.add(Category(title = "Fruits2", values = fruits))
        categories.add(Category(title = "Fruits3", values = fruits))
        categories.add(Category(title = "Fruits4", values = fruits))
        categories.add(Category(title = "Fruits5", values = fruits))
        categories.add(Category(title = "Fruits6", values = fruits))
        categories.add(Category(title = "Fruits7", values = fruits))
        categories.add(Category(title = "Fruits8", values = fruits))
        categories.add(Category(title = "Fruits9", values = fruits))
        categories.add(Category(title = "Fruits10", values = fruits))
        categories.add(Category(title = "Fruits11", values = fruits))
        categories.add(Category(title = "Fruits12", values = fruits))
        categories.add(Category(title = "Fruits13", values = fruits))
        categories.add(Category(title = "Fruits14", values = fruits))
        categories.add(Category(title = "Fruits15", values = fruits))
        categories.add(Category(title = "Cars", values = cars))
        categories.add(Category(title = "Cars1", values = cars))
        categories.add(Category(title = "Cars2", values = cars))
        categories.add(Category(title = "Cars3", values = cars))
        dummyData = Data(categories)
        favList=ArrayList<String>()
    }

    fun getCategories(): ArrayList<Category> {
//        var categories=ArrayList<String>()
//        dummyData.data.forEach {
//            categories.add(it.title)
//        }
        return dummyData.data
    }

    fun getSubCategories(category: Category?): ArrayList<String> {
        return category?.values ?: ArrayList()
    }

    fun addFavourite(item:String){
        if(!favList.contains(item)){
            favList.add(item)
        }
    }

    fun deleteFavourite(item:String){
        favList.remove(item)
    }

}