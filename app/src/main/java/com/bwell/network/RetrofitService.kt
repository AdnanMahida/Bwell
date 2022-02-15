package com.bwell.network

import com.bwell.BaseApp.Companion.appContext
import com.bwell.util.Const
import com.bwell.util.hasNetwork
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    const val cacheSize = (5 * 1024 * 1024).toLong()
    private val myCache = Cache(appContext.cacheDir, cacheSize)

    val okHttpClient = OkHttpClient.Builder()
        .cache(myCache)
        .addInterceptor { chain ->
            var request = chain.request()
            request = if (hasNetwork(appContext)!!)
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
            chain.proceed(request)
        }
        .build()

    private var retrofit =
        Retrofit.Builder()
            .baseUrl(Const.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).build()

    fun <T> createService(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}