package com.example.yurich.keddit.features.news.adapter

import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.yurich.keddit.R
import com.example.yurich.keddit.commons.RedditNewsItem
import com.example.yurich.keddit.commons.adapter.ViewType
import com.example.yurich.keddit.commons.adapter.ViewTypeDelegateAdapter
import com.example.yurich.keddit.commons.extensions.getFriendlyTime
import com.example.yurich.keddit.commons.extensions.inflate
import com.example.yurich.keddit.commons.extensions.loadImg
import kotlinx.android.synthetic.main.news_item.view.*

class NewsDelegateAdapter() : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val newsViewHolder = NewsViewHolder(parent)
        return newsViewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as NewsViewHolder
        holder.bind(item as RedditNewsItem)
    }

    inner class NewsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item)
    ) {

        init {
            itemView.setOnClickListener {
                val pos = this.adapterPosition
            }
        }

        fun bind(item: RedditNewsItem) = with(itemView) {
            img_thumbnail.loadImg(item.thumbnail)
            description.text = item.title
            author.text = item.author
            comments.text = "${item.numComments} comments"
            time.text = item.created.getFriendlyTime()
        }
    }
}