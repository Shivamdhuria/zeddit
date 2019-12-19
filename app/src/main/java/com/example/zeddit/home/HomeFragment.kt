package com.example.zeddit.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.zeddit.R
import com.example.zeddit.base.BaseFragment
import com.example.zeddit.ext.observe
import com.example.zeddit.utils.Resource.Status.*
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclas for displaying list of posts.
 */
class HomeFragment : BaseFragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(activity, "Home", Toast.LENGTH_LONG).show()
        button.setOnClickListener {
            viewModel.getNews().observe(this) {
                Log.e("HomeView", it.toString())
                when (it.status) {
                    ERROR -> {
                    }
                    SUCCESS -> {
                    }
                    LOADING -> {
                    }
                }
            }
        }
    }
}
