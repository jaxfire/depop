package com.jaxfire.depop.data.repository

import com.jaxfire.depop.data.repository.entity.Product

interface ProductRepository {
    suspend fun getAllProducts(): ResultWrapper<List<Product>>
}