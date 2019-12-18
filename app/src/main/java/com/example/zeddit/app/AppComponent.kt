package com.example.zeddit.app

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        AppModule::class,
        AppBuilderModule::class]
)
internal interface AppComponent : AndroidInjector<ZedditApplication> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<ZedditApplication>
}