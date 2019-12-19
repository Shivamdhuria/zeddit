package com.example.zeddit.app

import com.example.zeddit.BuildConfig
import com.example.zeddit.app.AppConstant.REDDIT_BASE_URL
import com.example.zeddit.utils.AuthenticationInterceptor
import com.example.zeddit.utils.LiveDataCallAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
internal class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gsonBuilder: Gson): Retrofit = retrofitConfiguration(client, gsonBuilder)

    private fun retrofitConfiguration(client: OkHttpClient, gsonBuilder: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(REDDIT_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthenticationInterceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(1, TimeUnit.MINUTES)
        httpClient.connectTimeout(1, TimeUnit.MINUTES)
        httpClient.writeTimeout(1, TimeUnit.MINUTES)
        httpClient.addInterceptor(loggingInterceptor())
        httpClient.addInterceptor(authInterceptor)
        return httpClient.build()
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }
}