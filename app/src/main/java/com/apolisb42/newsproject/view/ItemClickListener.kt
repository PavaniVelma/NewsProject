package com.apolisb42.newsproject.view

import com.apolisb42.newsproject.model.New

interface ItemClickListener {

    fun send(news: New)
    fun delete(id: String)

    fun isSelected(news:New)
}