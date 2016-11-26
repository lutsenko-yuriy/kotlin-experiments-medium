package com.example.yurich.keddit.di.news

import com.example.yurich.keddit.NewsFragment
import com.example.yurich.keddit.di.AppModule
import com.example.yurich.keddit.di.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NewsModule::class,
        NetworkModule::class
))
interface NewsComponent {
    fun inject(newsFragment: NewsFragment)
}