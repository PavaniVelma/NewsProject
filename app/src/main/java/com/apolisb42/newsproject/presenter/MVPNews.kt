package com.apolisb42.newsproject.presenter

import com.apolisb42.newsproject.model.NewsResponse

interface MVPNews {
    interface INewsPresenter{
        fun getNews()
    }

    interface NewsView{
        fun setResult(newsResponse: NewsResponse)
        fun setError(error:String)
    }
}