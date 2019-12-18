package com.example.zeddit.home

import androidx.lifecycle.ViewModel
import com.example.zeddit.app.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [HomeModule::class])
internal abstract class HomeActivityBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun homeFragment(): HomeFragment

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun loginViewModel(viewModel: HomeViewModel): ViewModel
}