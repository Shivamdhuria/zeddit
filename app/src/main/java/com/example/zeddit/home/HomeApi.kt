package com.example.zeddit.home

import com.example.zeddit.data.ListingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi{
    @GET("/top.json")
    fun getTop(@Query("after") after: String, @Query("limit") limit: String): Response<ListingResponse>
}