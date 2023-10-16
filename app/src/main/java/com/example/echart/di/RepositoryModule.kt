package com.example.echart.di

import com.example.echart.data.ChartRepository
import com.example.echart.data.IChartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRepository(repository: ChartRepository): IChartRepository
}

