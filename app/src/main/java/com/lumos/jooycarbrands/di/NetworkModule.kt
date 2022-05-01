package com.lumos.jooycarbrands.di

import com.google.gson.Gson
import com.lumos.jooycarbrands.BuildConfig
import com.lumos.jooycarbrands.data.source.brands.service.BrandService
import com.lumos.jooycarbrands.util.OkHttpHelper.getOkHttpBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun gson() = Gson()

    @Singleton
    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providesClientOkhttp(
        loggingInterceptor: HttpLoggingInterceptor
    ) = getOkHttpBuilder(loggingInterceptor).build()

    @Singleton
    @Provides
    fun providesBrandService(
        okHttpClient: OkHttpClient
    ) = provideService<BrandService>(okHttpClient)

    @Singleton
    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private inline fun <reified T : Any> provideService(
        okhttpClient: OkHttpClient
    ): T {
        return providesRetrofit(okhttpClient).create(T::class.java)
    }
}
