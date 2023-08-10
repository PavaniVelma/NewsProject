package com.apolisb42.newsproject.model

import android.content.ContentValues

class DaoNews( private val dbHelper:DbHelper) {

    fun delete(id:String):Int{
        val selection = "$ID=?"
        val selectionArgs = arrayOf(id)
        return dbHelper.writableDatabase.delete(TABLE_NAME, selection, selectionArgs)
    }

    fun fetchNews():List<New>{

        val newsList = mutableListOf<New>()

        val cursor = dbHelper.readableDatabase.query(TABLE_NAME, null,null,null,null,null,null)

        while(cursor.moveToNext()){

            val id  = cursor.getString(cursor.getColumnIndexOrThrow(ID))
            val title  = cursor.getString(cursor.getColumnIndexOrThrow(TITLE))
            val image  = cursor.getString(cursor.getColumnIndexOrThrow(IMAGE))
            val author  = cursor.getString(cursor.getColumnIndexOrThrow(AUTHOR))
            val description  = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION))
            val url  = cursor.getString(cursor.getColumnIndexOrThrow(URL))
            val published  = cursor.getString(cursor.getColumnIndexOrThrow(PUBLISHED))
            val category  = cursor.getString(cursor.getColumnIndexOrThrow(CATEGORY))

            newsList.add(New(id=id,title=title,image=image,author=author,description=description,url=url,published=published,category= listOf(category)))
        }

        cursor.close()
        return newsList
    }
    fun insert(news: New):Long{
        val contentValue = ContentValues().apply{
            put(ID, news.id)
            put(TITLE, news.title)
            put(IMAGE, news.image)
            put(AUTHOR, news.author)
            put(DESCRIPTION, news.description)
            put(PUBLISHED, news.published)
            put(URL, news.url)
            put(CATEGORY, news.category.first())
        }

        return dbHelper.writableDatabase.insert(TABLE_NAME,null,contentValue)


    }

}