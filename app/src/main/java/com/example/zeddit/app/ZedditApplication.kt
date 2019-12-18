package com.example.zeddit.app

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

internal class ZedditApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}