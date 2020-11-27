package com.jaxfire.depop.data.repository

import com.jaxfire.depop.data.network.ProductApiService
import com.jaxfire.depop.data.repository.entity.Product
import com.jaxfire.depop.internal.NoConnectivityException
import retrofit2.HttpException

class ProductRepositoryImpl(
    private val productApiService: ProductApiService,
    private val productMapper: ProductMapper
) : ProductRepository {

    override suspend fun getAllProducts(): ResultWrapper<List<Product>> {

        try {
            return ResultWrapper.Success(productMapper.mapToDomainProducts(
                productApiService.getProducts().productData))

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
                    ResultWrapper.GenericError
                }
            }
        }
    }
}