package com.example.zeddit.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.zeddit.R
import javax.inject.Inject

/**
 * A simple [Fragment] subclas for displaying list of posts.
 */
class HomeFragment : Fragment() {

    @Inject
    internal lateinit var factory: ViewModelProvider.Factory


    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, factory).get(
            HomeViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}
