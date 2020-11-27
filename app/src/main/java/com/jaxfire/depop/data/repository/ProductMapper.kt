package com.jaxfire.depop.data.repository

import com.jaxfire.depop.data.network.response.ProductResponse
import com.jaxfire.depop.data.network.response.ProductResponse.ProductData.FormatData
import com.jaxfire.depop.data.repository.entity.Product
import com.jaxfire.depop.data.repository.entity.Product.Picture
import com.jaxfire.depop.data.repository.entity.Product.Picture.*

class ProductMapper {

    fun mapToDomainProducts(dataProducts: List<ProductResponse.ProductData>): List<Product> {
        return dataProducts.map { product ->
            Product(
                userId = product.id,
                fullDescription = product.description,
                shortDescription = generateShortDescription(product.description),
                pictures = product.picturesData.map { pictureData ->

                    val formats = HashMap<PictureSize, String>()

                    addFormat(formats, pictureData.formats.p1)
                    addFormat(formats, pictureData.formats.p2)
                    addFormat(formats, pictureData.formats.p4)
                    addFormat(formats, pictureData.formats.p5)
                    addFormat(formats, pictureData.formats.p6)
                    addFormat(formats, pictureData.formats.p7)
                    addFormat(formats, pictureData.formats.p8)

                    Picture(formats = formats)
                }
            )
        }
    }

    private fun addFormat(formats: HashMap<PictureSize, String>, formatData: FormatData.FormatData) {
        if (formatData.equals("null").not()) {
            formats[getSize(formatData.width)] = formatData.url
        }
    }

    private fun getSize(width: Int): PictureSize {
        return when {
            width > 960 -> PictureSize.XXL
            width > 640 -> PictureSize.XL
            width > 480 -> PictureSize.L
            width > 320 -> PictureSize.M
            width > 210 -> PictureSize.XS
            else -> PictureSize.XXS
        }
    }

    private fun generateShortDescription(description: String): String {
        var shortDescription = description.take(100)
        if (shortDescription.length < description.length) shortDescription = "$shortDescription..."
        return shortDescription
    }
}