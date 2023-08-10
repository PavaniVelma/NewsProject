package com.apolisb42.newsproject

import android.content.Context

class VolleyHAndler(private val context: Context) {

    companion object {

        const val BASE_URL_NEWS = "https://api.currentsapi.services/v1/latest-news"
        const val BASE_SEARCH_URL_NEWS = "https://api.currentsapi.services/v1/search?"
    }
}