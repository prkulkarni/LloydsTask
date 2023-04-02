package com.example.lloydstask.di

import android.content.Context
import com.example.lloydstask.ui.wrapper.GlideImageLoader
import com.example.lloydstask.ui.wrapper.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object ImageLoaderModule {
    @Provides
    fun provideImageLoader(@ApplicationContext context: Context): ImageLoader =
        GlideImageLoader(context)
}