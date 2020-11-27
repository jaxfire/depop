package com.jaxfire.depop.data.repository

import com.jaxfire.depop.data.network.response.ProductResponse
import com.jaxfire.depop.data.repository.entity.Product

class ProductMapper() {

    fun mapToDomainProducts(dataProducts: List<ProductResponse.ProductData>): List<Product> {
        return dataProducts.map { product ->
            Product(
                userId = product.id,
                fullDescription = product.description,
                shortDescription = generateShortDescription(product.description),
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
        }
    }

    private fun generateShortDescription(description: String): String {
        var shortDescription = description.take(100)
        if (shortDescription.length < description.length) shortDescription = "$shortDescription..."
        return shortDescription
    }
}