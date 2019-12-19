package com.example.zeddit.utils

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class AuthenticationInterceptor @Inject constructor() :
    Interceptor {

    private companion object {}

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val request = builder.build()
        return chain.proceed(request)
    }
}