package com.apolisb42.newsproject.model

interface ResponseCallback {

    fun success(newsResponse: NewsResponse)

    fun failure(error:String)
}