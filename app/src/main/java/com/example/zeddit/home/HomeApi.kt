package com.example.zeddit.home

import androidx.lifecycle.LiveData
import com.example.zeddit.data.DataResponse
import com.example.zeddit.utils.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {
    @GET("/top.json")
    fun getTop(@Query("after") after: String, @Query("limit") limit: String): LiveData<GenericApiResponse<DataResponse>>
}