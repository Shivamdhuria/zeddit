package com.example.zeddit.home

import androidx.lifecycle.LiveData
import com.example.zeddit.data.ListingResponse
import com.example.zeddit.data.Post
import com.example.zeddit.data.PostResponse
import com.example.zeddit.utils.GenericApiResponse
import com.example.zeddit.utils.Resource

internal interface HomeRepository {

    fun fetchTopNews(): LiveData<Resource<List<Post>>>

    fun cancelJob()
}