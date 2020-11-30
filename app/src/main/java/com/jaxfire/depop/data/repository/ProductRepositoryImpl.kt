package com.jaxfire.depop.data.repository

import android.util.Log
import com.jaxfire.depop.data.network.ProductApiService
import com.jaxfire.depop.data.network.RemoteDataSource
import com.jaxfire.depop.data.repository.entity.Product
import com.jaxfire.depop.internal.NoConnectivityException
import retrofit2.HttpException

class ProductRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val productMapper: ProductMapper
) : ProductRepository {

    override suspend fun getAllProducts(): ResultWrapper<List<Product>> {

        try {
            return ResultWrapper.Success(
                productMapper.mapToDomainProducts(remoteDataSource.getProducts())
            )
        } catch (throwable: Throwable) {
            return when (throwable) {
                is NoConnectivityException -> {
                    ResultWrapper.NetworkError
                }
                is HttpException -> {
                    // TODO: Send HTTP error to cloud reporting service - throwable.code()
                    ResultWrapper.GenericError
                }
                else -> {
                    Log.d("jim", "Generic error: ${throwable.message}")
                    ResultWrapper.GenericError
                }
            }
        }
    }
}