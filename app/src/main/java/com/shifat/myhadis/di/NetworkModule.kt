package com.shifat.myhadis.di

import com.shifat.myhadis.api.HadisApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://20.197.0.148:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesHadisApi(retrofit: Retrofit): HadisApi {
        return retrofit.create(HadisApi::class.java)
    }
}