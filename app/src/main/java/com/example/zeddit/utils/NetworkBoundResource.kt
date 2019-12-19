package com.example.zeddit.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.zeddit.app.AppConstant.NETWORK_TIMEOUT
import com.example.zeddit.error.ErrorHandling
import com.example.zeddit.error.ErrorHandling.Companion.ERROR_CHECK_NETWORK_CONNECTION
import com.example.zeddit.error.ErrorHandling.Companion.ERROR_UNKNOWN
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

/*
Response Object is returned from the Api Call where Cache object is the one from the
 database that is always displayed ideally.
* */


abstract class NetworkBoundResource<ResponseObject, CacheObject>
    (
    isNetworkAvailable: Boolean, // is there a network connection?
    isNetworkRequest: Boolean, // is this a network request?
    shouldCancelIfNoInternet: Boolean, // should this job be cancelled if there is no network?
    shouldLoadFromCache: Boolean // should the cached data be loaded initially?
) {

    private val TAG: String = "AppDebug"

    protected val result = MediatorLiveData<Resource<CacheObject>>()
    protected lateinit var job: CompletableJob
    protected lateinit var coroutineScope: CoroutineScope

    init {
        setJob(initNewJob())
        //Initially setting up loading
        setValue(Resource.loading(null))

        if (shouldLoadFromCache) {
            //ignore for now

            // view cache to start
//            val dbSource = loadFromCache()
//            result.addSource(dbSource) {
//                result.removeSource(dbSource)
//                setValue(DataState.loading(isLoading = true, cachedData = it))
//            }
        }

        if (isNetworkRequest) {
            if (isNetworkAvailable) {
                doNetworkRequest()
            } else {
//                if (shouldCancelIfNoInternet) {
//                    onErrorReturn(
//                        ErrorHandling.UNABLE_TODO_OPERATION_WO_INTERNET
//                    )
//                } else {
//                    doCacheRequest()
//                }
            }
        } else {
//            doCacheRequest()
        }
    }

    fun doCacheRequest() {
        coroutineScope.launch {

            // View data from cache only and return
            createCacheRequestAndReturn()
        }
    }

    fun doNetworkRequest() {
        coroutineScope.launch {

            Log.e(TAG,"Network request Started.......")

            // simulate a network delay for testing

            withContext(Main) {

                // make network call
                val apiResponse = createCall()
                result.addSource(apiResponse) { response ->
                    Log.e(TAG,"Netwrk request Started and processed $response")
                    result.removeSource(apiResponse)

                    coroutineScope.launch {
                        Log.e(TAG,"Netwrk request Started and processed $response")
                        handleNetworkCall(response)
                    }
                }
            }
        }

//        GlobalScope.launch(IO) {
//            delay(NETWORK_TIMEOUT)
//
//            if (!job.isCompleted) {
//                Log.e(TAG, "NetworkBoundResource: JOB NETWORK TIMEOUT.")
//                job.cancel(CancellationException(ErrorHandling.UNABLE_TO_RESOLVE_HOST))
//            }
//        }
    }

    suspend fun handleNetworkCall(response: GenericApiResponse<ResponseObject>) {
        Log.e(TAG, "Handle Network Call ${response.toString()}")
        when (response) {
            is ApiSuccessResponse -> {
                Log.e(TAG, "NetworkBoundResource: SUCCESS ${response.toString()}")
                handleApiSuccessResponse(response)
            }
            is ApiErrorResponse -> {
                Log.e(TAG, "NetworkBoundResource: ${response.errorMessage}")
                onErrorReturn(response.errorMessage)
            }
            is ApiEmptyResponse -> {
                Log.e(TAG, "NetworkBoundResource: Request returned NOTHING (HTTP 204).")
                onErrorReturn("HTTP 204. Returned NOTHING.")
            }
        }
    }

    fun onCompleteJob(dataState: Resource<CacheObject>) {
        GlobalScope.launch(Main) {
            job.complete()
            setValue(dataState)
        }
    }

    fun onErrorReturn(errorMessage: String?) {
        var msg = errorMessage
        if (msg == null) {
            msg = ERROR_UNKNOWN
        } else if (ErrorHandling.isNetworkError(msg)) {
            msg = ERROR_CHECK_NETWORK_CONNECTION
        }

        onCompleteJob(Resource.error(msg, null))
    }

    fun setValue(dataState: Resource<CacheObject>) {
        result.value = dataState
    }

    @UseExperimental(InternalCoroutinesApi::class)
    private fun initNewJob(): Job {
        Log.d(TAG, "initNewJob: called.")
        job = Job() // create new job
        job.invokeOnCompletion(
            onCancelling = true,
            invokeImmediately = true,
            handler = object : CompletionHandler {
                override fun invoke(cause: Throwable?) {
                    if (job.isCancelled) {
                        Log.e(TAG, "NetworkBoundResource: Job has been cancelled.")
                        cause?.let {
                            onErrorReturn(it.message)
                        } ?: onErrorReturn("Unknown error.")
                    } else if (job.isCompleted) {
                        Log.e(TAG, "NetworkBoundResource: Job has been completed.")
                        // Do nothing? Should be handled already
                    }
                }
            })
        coroutineScope = CoroutineScope(IO + job)
        return job
    }

    fun asLiveData() = result

    abstract suspend fun createCacheRequestAndReturn()

    abstract suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<ResponseObject>)

    abstract fun createCall(): LiveData<GenericApiResponse<ResponseObject>>

    abstract fun loadFromCache(): LiveData<CacheObject>

    abstract suspend fun updateLocalDb(cacheObject: CacheObject?)

    abstract fun setJob(job: Job)

}














