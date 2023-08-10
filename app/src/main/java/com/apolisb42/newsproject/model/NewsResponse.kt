package com.apolisb42.newsproject.model

data class NewsResponse(
    val news: List<New>,
    val page: Int,
    val status: String
)