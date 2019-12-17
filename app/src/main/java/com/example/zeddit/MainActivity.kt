package com.example.zeddit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coordinator_layout.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }
}
