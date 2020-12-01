package com.jaxfire.depop.data.network

import com.jaxfire.depop.data.network.response.ProductResponse.ProductData

interface RemoteDataSource {
    suspend fun getPopularProducts(): List<ProductData>
}
