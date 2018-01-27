package com.example.nurbekkabylbai.sch.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

/**
 * Created by Nurbek Kabylbay on 27.01.2018.
 */
object RetrofitClient {

    private val baseUrl: String = "" // TODO: it changes
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        return retrofit ?: createClient()
    }

    private fun createClient() : Retrofit {
        val httpClient = OkHttpClient.Builder()
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logger)

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient.build())
                .build()
    }
}