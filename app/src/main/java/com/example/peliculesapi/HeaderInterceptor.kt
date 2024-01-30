package com.example.peliculesapi

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response { //Respuesta del servidor
        val request = chain.request().newBuilder()
            .addHeader(
                "X-RapidAPI-Key", "9c9534eda0mshdf6ff6d44917f79p1ef936jsna94ce468adc8"
            )
            .addHeader("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com")
            .build()
        return chain.proceed(request)
    }
}