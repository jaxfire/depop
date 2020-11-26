package com.jaxfire.depop.data.repository

import android.util.Log
import com.jaxfire.depop.data.network.ProductApiService
import com.jaxfire.depop.data.repository.entity.Product
import com.jaxfire.depop.internal.NoConnectivityException

class NonCachingProductRepository(private val productApiService: ProductApiService) :
    ProductRepository {

    override suspend fun getAllProducts(): List<Product> {

        try {


            // Convert from Data layer object to Domain object and return
//
//            val products = productApiService.getProducts().products.map { product ->
//                Product(
//                    userId = product.id,
//                    description = product.description,
//                    pictures = product.picturesData.map { pictureData ->
//                        val pictures = mutableListOf<Product.Picture>()
//                        if (pictureData.formats.p0.equals("null").not()) {
//                            pictures.add(
//                                Product.Picture(
//                                    size = Pair(
//                                        pictureData.formats.p0.width,
//                                        pictureData.formats.p0.width
//                                    ),
//                                    url = pictureData.formats.p0.url
//                                )
//                            )
//                        }
//                    }
//
//                        )
//                    }
//                )
//            }

//            products.products.forEach {
////            Log.d("jim", "Address: ${it.id}")
////            Log.d("jim", "Address: ${it.description}")
//                it.picturesData.forEach {
//                    Log.d("jim", "P0: ${it.formats.p0}")
//                    Log.d("jim", "P1: ${it.formats.p1}")
//                    Log.d("jim", "P2: ${it.formats.p2}")
//                    Log.d("jim", "P3: ${it.formats.p3}")
//                    Log.d("jim", "P4: ${it.formats.p4}")
//                    Log.d("jim", "P5: ${it.formats.p5}")
//                    Log.d("jim", "P6: ${it.formats.p6}")
//                    Log.d("jim", "P7: ${it.formats.p7}")
//                    Log.d("jim", "P8: ${it.formats.p8}")
//                    Log.d("jim", "P9: ${it.formats.p9}")
//                }
//                Log.d("jim", "\n")
//                Log.d("jim", "\n")
//                Log.d("jim", "\n")
//            }

        } catch (e: NoConnectivityException) {
            Log.d("jim", "No connectivity")
        }

        Log.d("jim", "I'm the repo")
        return emptyList()
    }
}