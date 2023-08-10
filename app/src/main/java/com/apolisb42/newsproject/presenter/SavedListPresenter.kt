package com.apolisb42.newsproject.presenter

import com.apolisb42.newsproject.model.DaoNews
import com.apolisb42.newsproject.model.New
import com.apolisb42.newsproject.model.VolleyHandler

class SavedListPresenter(private val volleyHandler: VolleyHandler,
                         private val daoNews: DaoNews) {
    fun getImageLoader() = volleyHandler.getImageLoader()

    fun fetchNews():List<New>{
        return daoNews.fetchNews()
    }
}