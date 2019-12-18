package com.example.zeddit.home

import androidx.lifecycle.LiveData
import com.example.zeddit.data.ListingResponse
import com.example.zeddit.utils.GenericApiResponse

internal interface HomeRepository {

    fun fetchTopNews(): LiveData<GenericApiResponse<ListingResponse>>
}