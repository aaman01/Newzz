package com.example.newzz

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)