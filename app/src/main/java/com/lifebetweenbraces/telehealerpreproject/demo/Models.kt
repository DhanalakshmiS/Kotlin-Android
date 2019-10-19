package com.lifebetweenbraces.telehealerpreproject.demo

/**
 * Created by Dhanalakshmi Srinivasan on 2019-10-19.
 */

interface BaseModel

data class Data(
    val data: ArrayList<Category>
):BaseModel

data class Category(
    val id: String="",
    val title: String,
    val values: ArrayList<String>
):BaseModel


