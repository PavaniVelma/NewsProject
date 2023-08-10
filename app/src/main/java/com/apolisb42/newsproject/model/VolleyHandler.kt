package com.apolisb42.newsproject.model

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.util.LruCache
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.StringRequest
import com.apolisb42.newsproject.VolleyHAndler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URLEncoder

class VolleyHandler(context: Context) {

    private var requestQueue: RequestQueue = RequestQueue(
        DiskBasedCache(context.cacheDir, 100000),
        BasicNetwork(HurlStack())
    )

    fun sendGetTypeRequest(responseCallback: ResponseCallback){
        val stringRequest = object: StringRequest(
            Method.GET,
            VolleyHAndler.BASE_URL_NEWS,
            {
                Log.i("tag", it)

                val typeToken = object: TypeToken<NewsResponse>(){}

                val response = Gson().fromJson(it,typeToken)

                if(response.status == "ok") {
                    responseCallback.success(response)
                }

            },{
                Log.i("tag","$it")
                responseCallback.failure(it.toString())

            },
        ){
            override fun getHeaders(): MutableMap<String, String> {
                val header = HashMap<String, String>()
                header["Authorization"] = "QBnJRAfCr80hyzsIR2MAnASXMUl_Y30gjPJtx0EKjNGEa4kO"
                return header
            }

        }
        requestQueue.add(stringRequest)
    }
    fun sendGetTypeRequestForSearch(responseCallback: ResponseCallback, search:String){

        val queryParams = "category=${URLEncoder.encode(search,Charsets.UTF_8.name())}"
        val url = "${VolleyHAndler.BASE_SEARCH_URL_NEWS}$queryParams"
        val stringRequest = object: StringRequest(
            Method.GET,
            url,
            {
                Log.i("tag", it)

                val typeToken = object: TypeToken<NewsResponse>(){}

                val response = Gson().fromJson(it,typeToken)

                if(response.status == "ok") {
                    responseCallback.success(response)
                }

            },{
                Log.i("tag","$it")
                responseCallback.failure(it.toString())

            },
        ){
            override fun getHeaders(): MutableMap<String, String> {
                val header = HashMap<String, String>()
                header["Authorization"] = "QBnJRAfCr80hyzsIR2MAnASXMUl_Y30gjPJtx0EKjNGEa4kO"
                return header
            }

        }
        requestQueue.add(stringRequest)
    }

    fun getImageLoader(): ImageLoader{

        requestQueue.start()
        val imageLoader = ImageLoader(
            requestQueue,
            object : ImageLoader.ImageCache {
                val cache = LruCache<String, Bitmap>(50)
                override fun getBitmap(url: String?): Bitmap? = cache[url]

                override fun putBitmap(url: String?, bitmap: Bitmap?) {
                    cache.put(url, bitmap)
                }

            }
        )
        return imageLoader
    }



}