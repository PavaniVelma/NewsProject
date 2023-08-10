package com.apolisb42.newsproject.model

const val DATABASE_VERSION =2
const val DATABASE_NAME = "MyNewsLib"
const val TABLE_NAME = "News"


const val ID = "id"
const val IMAGE ="image"
const val TITLE ="title"
const val DESCRIPTION = "description"
const val AUTHOR = "author"
const val PUBLISHED = "published"
const val URL = "url"
const val CATEGORY ="category"


val CREATE_NEWS_TABLE  = """
           CREATE TABLE $TABLE_NAME(
           $ID TEXT PRIMARY KEY,
           $IMAGE TEXT, 
           $TITLE TEXT,
           $DESCRIPTION TEXT,
           $AUTHOR TEXT,
           $PUBLISHED TEXT,
           $URL TEXT,
           $CATEGORY TEXT)
       """.trimIndent()