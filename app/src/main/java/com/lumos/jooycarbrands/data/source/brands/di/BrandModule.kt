package com.lumos.jooycarbrands.data.source.brands.di

import com.lumos.jooycarbrands.data.source.brands.BrandDataSource
import com.lumos.jooycarbrands.data.source.brands.BrandRepository
import com.lumos.jooycarbrands.data.source.brands.mapper.BrandDataMapper
import com.lumos.jooycarbrands.data.source.brands.mapper.BrandDataMapperImpl
import com.lumos.jooycarbrands.data.source.brands.mapper.ModelDataMapper
import com.lumos.jooycarbrands.data.source.brands.mapper.ModelDataMapperImpl
import com.lumos.jooycarbrands.data.source.brands.remote.BrandRemoteDataSource
import com.lumos.jooycarbrands.data.source.brands.remote.BrandRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class BrandModule {

    @Binds
    abstract fun bindBrandDataMapper(brandDataMapper: BrandDataMapperImpl): BrandDataMapper

    @Binds
    abstract fun bindModelDataMapper(modelDataMapper: ModelDataMapperImpl): ModelDataMapper

    @Binds
    abstract fun bindBrandRemoteDataSource(brandRemoteDataSource: BrandRemoteDataSourceImpl): BrandRemoteDataSource

    @Binds
    abstract fun bindBrandDataSource(brandDataSource: BrandRepository): BrandDataSource
}
