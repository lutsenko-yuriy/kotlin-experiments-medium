package com.example.yurich.keddit.api

import retrofit2.Call
import javax.inject.Inject

class NewsRestApi @Inject constructor(private val redditApi: RedditApi) : NewsApi {

    override fun getNews(after: String, limit: String): Call<RedditNewsResponse> {
        return redditApi.getTop(after, limit)
    }

}