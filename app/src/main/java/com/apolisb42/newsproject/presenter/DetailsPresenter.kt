package com.apolisb42.newsproject.presenter

import com.apolisb42.newsproject.model.VolleyHandler

class DetailsPresenter(private val volleyHandler: VolleyHandler) {
     fun getImageLoader() = volleyHandler.getImageLoader()
}