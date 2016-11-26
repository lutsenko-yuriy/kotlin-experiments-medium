package com.example.yurich.keddit.di.news

import com.example.yurich.keddit.api.NewsApi
import com.example.yurich.keddit.api.NewsRestApi
import com.example.yurich.keddit.api.RedditApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NewsModule {
    @Provides
    @Singleton
    fun provideNewsApi(redditApi: RedditApi): NewsApi {
        return NewsRestApi(redditApi)
    }

    @Provides
    @Singleton
    fun provideRedditApi(retrofit: Retrofit): RedditApi {
        return retrofit.create(RedditApi::class.java)
    }
}