package com.example.zeddit.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.zeddit.data.Post
import com.example.zeddit.utils.Resource
import javax.inject.Inject

internal class HomeViewModel @Inject constructor(private val repository: HomeRepository) :
    ViewModel() {
    fun getNews(): LiveData<Resource<List<Post>>> {
        return repository.fetchTopNews()
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelJob() // cancel active jobs
    }
}
