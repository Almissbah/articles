package com.almissbah.articles.ui.home.adapter

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.almissbah.articles.data.remote.model.Article

class OrdersDiffCallback(
    private val oldList: MutableList<Article>,
    private val newList: List<Article>
) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList == newList
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList == newList
    }


    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return newList[newItemPosition]
    }

}