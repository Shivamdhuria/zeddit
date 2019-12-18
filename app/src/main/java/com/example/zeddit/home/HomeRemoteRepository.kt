package com.example.zeddit.home

import androidx.lifecycle.LiveData
import com.example.zeddit.data.ListingResponse
import com.example.zeddit.utils.AbsentLiveData
import com.example.zeddit.utils.GenericApiResponse
import javax.inject.Inject

internal open class HomeRemoteRepository @Inject constructor(private val api: HomeApi) :
    HomeRepository {

    override fun fetchTopNews(): LiveData<GenericApiResponse<ListingResponse>> {
        return AbsentLiveData.create()
    }

}