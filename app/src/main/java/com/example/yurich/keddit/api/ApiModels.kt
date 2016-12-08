package com.example.yurich.keddit.api

class RedditNewsResponse(val data: RedditDataResponse)

class RedditDataResponse(
        val children: List<RedditChildrenResponse>,
        val after: String?,
        val before: String?
)

class RedditChildrenResponse(val data: RedditNewsDataResponse)

class RedditNewsDataResponse(
        val author: String,
        val title: String,
        val num_comments: Int,
        val created: Long,
        val preview: RedditPreview?,
        val url: String
)

class RedditPreview (
        val images: List<RedditImage>
)

class RedditImage (
        val source: RedditSource
)

class RedditSource (
        val url: String
)
