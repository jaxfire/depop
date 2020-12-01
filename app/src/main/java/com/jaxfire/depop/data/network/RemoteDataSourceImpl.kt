package com.jaxfire.depop.data.network

import com.jaxfire.depop.data.network.response.ProductResponse.ProductData

class RemoteDataSourceImpl(
    private val productApiService: ProductApiService,
): RemoteDataSource {

    override suspend fun getPopularProducts(): List<ProductData> {
        val productData = productApiService.getPopularProducts().productData
        return productData ?: listOf()
    }
}