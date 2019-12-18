package com.example.zeddit.home

import androidx.lifecycle.ViewModel
import javax.inject.Inject

internal class HomeViewModel @Inject constructor(private val repository: HomeRepository) :
    ViewModel() {
    fun getNews() {
    repository.fetchTopNews()
    }
}
