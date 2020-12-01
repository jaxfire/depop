package com.jaxfire.depop.data.repository

import com.jaxfire.depop.data.network.RemoteDataSource
import com.jaxfire.depop.data.repository.entity.Product
import com.jaxfire.depop.internal.NoConnectivityException
import retrofit2.HttpException

class ProductRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val productMapper: ProductMapper,
) : ProductRepository {

    override suspend fun getPopularProducts(): ResultWrapper<List<Product>> {

        try {
            return ResultWrapper.Success(
                productMapper.mapToDomainProducts(remoteDataSource.getPopularProducts())
            )
        } catch (throwable: Throwable) {
            return when (throwable) {
                is NoConnectivityException -> {
                    ResultWrapper.NetworkConnectivityError
                }
                is HttpException -> {
                    // Example of cloud error reporting
                    if (throwable.code() >= 500) {
                        // CloudReporter.reportServerError()
                    }
                    ResultWrapper.GenericError
                }
                else -> {
                    ResultWrapper.GenericError
                }
            }
        }
    }
}