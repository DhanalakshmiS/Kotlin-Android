package com.lifebetweenbraces.telehealerpreproject.demo.view.adapters

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lifebetweenbraces.telehealerpreproject.R
import com.lifebetweenbraces.telehealerpreproject.demo.Category
import com.lifebetweenbraces.telehealerpreproject.util.Callback
import com.lifebetweenbraces.telehealerpreproject.util.Constants

/**
 * Created by Dhanalakshmi Srinivasan on 2019-10-19.
 */
class ChipsAdapter(val app: Application, val type: Int) :RecyclerView.Adapter<ChipsAdapter.ChipViewHolder>() {

    var itemClickListener:Callback<Any>?=null
    var items:ArrayList<*>?=null

    override fun getItemCount(): Int {
        return items?.size?:0
    }

    override fun onBindViewHolder(holder: ChipViewHolder, position: Int) {
        var item=items?.get(position)
        holder?.textItem?.text = when(type){
            Constants.TYPE_CATEGORY -> {
                var category=item as Category
                holder.itemView.setOnClickListener {
                    itemClickListener?.invoke(category)
                }
                category.title
            }
            else -> {
                var subCategory=item as String
                holder.itemView.setOnClickListener {
                    itemClickListener?.invoke(subCategory)
                }
                subCategory
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipViewHolder {
        var id = when (type) {
            Constants.TYPE_CATEGORY -> R.layout.list_item_category
            Constants.TYPE_SUB_CATEGORY -> R.layout.list_item_sub_category
            else -> R.layout.list_item_favourite
        }
        var view = LayoutInflater.from(app).inflate(id, parent, false)
        return ChipViewHolder(view)
    }

    inner class ChipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textItem: TextView? = itemView?.findViewById(R.id.name)
    }
}