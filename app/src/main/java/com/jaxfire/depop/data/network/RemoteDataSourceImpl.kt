package com.jaxfire.depop.data.network

import com.jaxfire.depop.data.network.response.ProductResponse.ProductData

class RemoteDataSourceImpl(
    private val productApiService: ProductApiService,
): RemoteDataSource {

    /*
    * Get the raw product data
    * If the data is unusable. i.e. productData is null then it will return an empty list.
     */
    override suspend fun getProducts(): List<ProductData> {
        val productData = productApiService.getPopularProducts().productData
        return productData ?: listOf()
    }
}