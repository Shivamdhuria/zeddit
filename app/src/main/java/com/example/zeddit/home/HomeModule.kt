package com.example.zeddit.home

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal class HomeModule {

    @Provides
    internal fun providesApi(retrofit: Retrofit): HomeApi = retrofit.create(HomeApi::class.java)

    @Provides
    internal fun providesRepository(api: HomeApi): HomeRepository = HomeRemoteRepository(api)
}