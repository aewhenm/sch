package com.example.nurbekkabylbai.sch.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Nurbek Kabylbay on 27.01.2018.
 */
object RetrofitClient {

    private val baseUrl: String = "http://10.42.0.1:8080/" // TODO: it changes
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
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}