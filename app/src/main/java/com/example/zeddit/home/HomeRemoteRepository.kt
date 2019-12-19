package com.example.zeddit.home

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.zeddit.data.DataResponse
import com.example.zeddit.data.Post
import com.example.zeddit.utils.*
import kotlinx.coroutines.Job
import javax.inject.Inject

internal open class HomeRemoteRepository @Inject constructor(private val api: HomeApi) :
    HomeRepository, JobManager("HomeRepository") {

    override fun fetchTopNews(): LiveData<Resource<List<Post>>> {
        return object : NetworkBoundResource<DataResponse, List<Post>>(
            true,
            true,
            true,
            false
        ) {
            override suspend fun createCacheRequestAndReturn() {
                //ignore for now
            }

            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<DataResponse>) {
                Log.e("repository success api handled", response.toString())
            }

            override fun createCall(): LiveData<GenericApiResponse<DataResponse>> {
                return api.getTop("", "10")
            }

            override fun loadFromCache(): LiveData<List<Post>> {
                return AbsentLiveData.create()
            }


            override suspend fun updateLocalDb(cacheObject: List<Post>?) {
                //ignore for now
            }

            override fun setJob(job: Job) {
                addJob("fetchTopNews", job)
            }

        }.asLiveData()
    }

    override fun cancelJob() {
        cancelActiveJobs()
    }

}
