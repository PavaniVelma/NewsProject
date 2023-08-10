package com.apolisb42.newsproject.presenter

import com.apolisb42.newsproject.model.DaoNews
import com.apolisb42.newsproject.model.New
import com.apolisb42.newsproject.model.NewsResponse
import com.apolisb42.newsproject.model.ResponseCallback
import com.apolisb42.newsproject.model.VolleyHandler

class NewsPresenter(private val volleyHandler:VolleyHandler,
                    private val daoNews: DaoNews,
                    private val newsView:MVPNews.NewsView): MVPNews.INewsPresenter {
    override fun getNews() {
        volleyHandler.sendGetTypeRequest(object : ResponseCallback{
            override fun success(newsResponse: NewsResponse) {
                newsView.setResult(newsResponse)
            }

            override fun failure(error: String) {
                newsView.setError(error)
            }
        })
    }

    fun searchNews(query:String){
        volleyHandler.sendGetTypeRequestForSearch(object:ResponseCallback{
            override fun success(newsResponse: NewsResponse) {
                newsView.setResult(newsResponse)
            }

            override fun failure(error: String) {
                newsView.setError(error)
            }

        },query)
    }


    fun getImageLoader() = volleyHandler.getImageLoader()

    fun insertNews(news: New){
        daoNews.insert(news)
    }

    fun deleteNews(id:String){
        daoNews.delete(id)
    }


}