package com.example.yurich.keddit

import android.app.Application
import com.example.yurich.keddit.di.AppModule
import com.example.yurich.keddit.di.news.DaggerNewsComponent
import com.example.yurich.keddit.di.news.NewsComponent

class KedditApp : Application() {

    companion object {
        lateinit var newsComponent: NewsComponent
    }

    @SuppressWarnings("deprecated")
    override fun onCreate() {
        super.onCreate()
        newsComponent = DaggerNewsComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}