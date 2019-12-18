package com.example.zeddit.utils

import okhttp3.Interceptor
import okhttp3.Response

internal class AuthenticationInterceptor() :
    Interceptor {

    private companion object {

        private const val TEMP_HEADER_NAME = "temp-authorization-token"
        private const val HEADER_NAME = "Authorization"
        private const val BEARER = "Bearer "
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val initial = chain.request()
        val builder = chain.request().newBuilder()
        val request = builder.build()
        return chain.proceed(request)
    }
}