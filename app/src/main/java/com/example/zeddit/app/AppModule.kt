package com.example.zeddit.app

import com.example.zeddit.app.AppConstant.REDDIT_BASE_URL
import com.example.zeddit.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
internal class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = retrofitConfiguration(client)

    private fun retrofitConfiguration(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(REDDIT_BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }
}