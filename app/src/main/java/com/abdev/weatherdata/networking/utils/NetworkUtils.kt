package com.abdev.weatherdata.networking.utils


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit


object NetworkUtils {
    val serverURL: String = "https://s3.eu-west-2.amazonaws.com"

    fun <S> createService(serviceClass: Class<S>) = getClient().create(serviceClass)!!

    private fun getClient(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(serverURL)
            .addConverterFactory(CustomConverter())
            .client(client)
            .build()
    }

}