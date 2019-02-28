package com.abdev.weatherdata.networking.utils

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class CustomConverter : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return if (String::class.java == type) {
            Converter<ResponseBody, String> { value -> value.string() }
        } else {
            GsonConverterFactory.create().responseBodyConverter(type, annotations, retrofit)
        }
    }
}