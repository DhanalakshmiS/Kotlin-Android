package com.lifebetweenbraces.telehealerpreproject.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel

/**
 * Created by Dhanalakshmi Srinivasan on 2019-10-19.
 */
interface BaseModel

abstract class BaseRecyclerAdapter<T : BaseRecyclerAdapter.BaseViewHolder, B : ViewDataBinding, M : BaseModel> : androidx.recyclerview.widget.RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder>() {

    var listItems: ArrayList<M> = ArrayList()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    abstract val layoutResId: Int
    //    abstract val emptyLayoutResId: Int

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    abstract fun getHolderInstance(binding: B): T
    //    abstract fun getEmptyHolderInstance(binding: EB): T


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = DataBindingUtil.inflate<B>(inflater, layoutResId, parent, false)
        return  getHolderInstance(binding = itemBinding)
    }

    override fun getItemId(position: Int): Long {
        return listItems[position].hashCode().toLong()
    }

    override fun onBindViewHolder(p0: BaseViewHolder, position: Int) {
        try {
            p0.bind(listItems[position])
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    abstract class ItemViewHolder<M : BaseModel>(itemBinding: ViewDataBinding) : BaseViewHolder(itemBinding) {
        abstract fun getBindingVariable(): Int
        abstract fun getViewModelInstance(item: M): AndroidViewModel
        override fun bind(item: BaseModel?) {
            val result = itemBinding.setVariable(getBindingVariable(), getViewModelInstance(item as M))
            if (!result) {
                throw RuntimeException("ViewModel variable not set. Check the types")
            }
        }
    }

    abstract class BaseViewHolder(val itemBinding: ViewDataBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemBinding.root) {
        abstract fun bind(item: BaseModel?)
    }

}