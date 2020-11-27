package com.jaxfire.depop.data.repository

import android.util.Log
import com.jaxfire.depop.data.network.ProductApiService
import com.jaxfire.depop.data.repository.entity.Product
import com.jaxfire.depop.internal.NoConnectivityException
import retrofit2.HttpException
import java.io.IOException

class ProductRepositoryImpl(private val productApiService: ProductApiService) : ProductRepository {

    override suspend fun getAllProducts(): List<Product> {

        val products = mutableListOf<Product>()

        try {

            products.addAll(productApiService.getProducts().productData.map { product ->
                Product(
                    userId = product.id,
                    description = product.description,
                    pictures = product.picturesData.map { pictureData ->

                        val formats = mutableListOf<Product.Picture.Format>()

                        if (pictureData.formats.p0.equals("null").not()) {
                            formats.add(
                                Product.Picture.Format(
                                    size = Pair(
                                        pictureData.formats.p0.width,
                                        pictureData.formats.p0.width
                                    ),
                                    url = pictureData.formats.p0.url
                                )
                            )
                        }

                        if (pictureData.formats.p1.equals("null").not()) {
                            formats.add(
                                Product.Picture.Format(
                                    size = Pair(
                                        pictureData.formats.p1.width,
                                        pictureData.formats.p1.width
                                    ),
                                    url = pictureData.formats.p1.url
                                )
                            )
                        }

                        if (pictureData.formats.p2.equals("null").not()) {
                            formats.add(
                                Product.Picture.Format(
                                    size = Pair(
                                        pictureData.formats.p2.width,
                                        pictureData.formats.p2.width
                                    ),
                                    url = pictureData.formats.p2.url
                                )
                            )
                        }

                        if (pictureData.formats.p4.equals("null").not()) {
                            formats.add(
                                Product.Picture.Format(
                                    size = Pair(
                                        pictureData.formats.p4.width,
                                        pictureData.formats.p4.width
                                    ),
                                    url = pictureData.formats.p4.url
                                )
                            )
                        }

                        if (pictureData.formats.p5.equals("null").not()) {
                            formats.add(
                                Product.Picture.Format(
                                    size = Pair(
                                        pictureData.formats.p5.width,
                                        pictureData.formats.p5.width
                                    ),
                                    url = pictureData.formats.p5.url
                                )
                            )
                        }

                        if (pictureData.formats.p6.equals("null").not()) {
                            formats.add(
                                Product.Picture.Format(
                                    size = Pair(
                                        pictureData.formats.p6.width,
                                        pictureData.formats.p6.width
                                    ),
                                    url = pictureData.formats.p6.url
                                )
                            )
                        }

                        if (pictureData.formats.p7.equals("null").not()) {
                            formats.add(
                                Product.Picture.Format(
                                    size = Pair(
                                        pictureData.formats.p7.width,
                                        pictureData.formats.p7.width
                                    ),
                                    url = pictureData.formats.p7.url
                                )
                            )
                        }

                        if (pictureData.formats.p8.equals("null").not()) {
                            formats.add(
                                Product.Picture.Format(
                                    size = Pair(
                                        pictureData.formats.p8.width,
                                        pictureData.formats.p8.width
                                    ),
                                    url = pictureData.formats.p8.url
                                )
                            )
                        }

                        Product.Picture(formats = formats)
                    }
                )
            })

            return products

        } catch (throwable: Throwable) {
            when (throwable) {
                is NoConnectivityException -> {
                    Log.d("jim", "No connectivity exception")
                }
                is HttpException -> {
                    val code = throwable.code()
                    Log.d("jim", "HttpException: $code")
//                    val errorResponse = convertErrorBody(throwable)
//                    ResultWrapper.GenericError(code, errorResponse)
                }
                else -> {
                    Log.d("jim", "Generic Error: ${throwable.message}")
//                    ResultWrapper.GenericError(null, null)
                }
            }
            // TODO: Handle. In call result?
//            Log.d("jim", "No connectivity")
            return emptyList()
        }
    }
}