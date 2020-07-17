package com.almissbah.articles.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.almissbah.articles.R
import com.almissbah.articles.data.remote.model.Article
import com.almissbah.articles.databinding.ArticlesListItemBinding
import com.almissbah.articles.utils.AppUtils
import com.almissbah.articles.utils.ArticlesUtils
import com.bumptech.glide.Glide


class ArticlesAdapter :
    RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    private var mList: MutableList<Article> = mutableListOf()
    var mItemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ArticleViewHolder {
        val context: Context = viewGroup.context
        val layoutInflater = LayoutInflater.from(context)
        val binding: ArticlesListItemBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.articles_list_item,
                viewGroup,
                false
            )

        return ArticleViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(
        holder: ArticleViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            val article = payloads[0] as Article
            updateItemView(holder, position, article)
        }
    }


    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article: Article = mList[holder.adapterPosition]
        holder.binding.article = article
        holder.binding.articleTitle.text = ArticlesUtils.trimArticleTitle(article.title)
        if (article.media.isNotEmpty() && article.media.last().metaData.isNotEmpty()) {
            val url = article.media.last().metaData.last().url
            if (AppUtils.isValidUrl(url))
                Glide
                    .with(holder.binding.root.context)
                    .load(article.media.last().metaData.last().url).fitCenter().circleCrop()
                    .into(holder.binding.ivArticleImage)
        }
        holder.binding.root.setOnClickListener {
            mItemClickListener?.onClicked(it, article, position)
        }
    }


    fun setData(newlist: List<Article>) {
        val diffCallback =
            ArticlesDiffCallback(
                mList,
                newlist
            )
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        mList = newlist.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }

    private fun updateItemView(holder: ArticleViewHolder, position: Int, article: Article) {
        mList[position] = article

    }

    class ArticleViewHolder(itemBinding: ArticlesListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val binding: ArticlesListItemBinding = itemBinding
    }


    interface ItemClickListener {
        fun onClicked(view: View, article: Article, position: Int)
    }
}