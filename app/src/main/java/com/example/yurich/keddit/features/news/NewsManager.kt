package com.example.yurich.keddit.features.news

import com.example.yurich.keddit.api.NewsApi
import com.example.yurich.keddit.commons.RedditNews
import com.example.yurich.keddit.commons.RedditNewsItem
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsManager @Inject constructor(private val api: NewsApi) {

    fun getNews(after: String, limit: String = "10"): Observable<RedditNews> {
        return Observable.create {
            subscriber ->

            val callResponse = api.getNews(after, limit)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val dataResponse = response.body().data
                val news = dataResponse.children.map {
                    val item = it.data
                    RedditNewsItem(
                            item.author,
                            item.title,
                            item.num_comments,
                            item.created,
                            if (item.preview != null)
                                item.preview.images[0].source.url
                            else
                                "",
                            item.url
                    )
                }

                val redditNews = RedditNews(
                        dataResponse.after ?: "",
                        dataResponse.before ?: "",
                        news
                )
                subscriber.onNext(redditNews)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}