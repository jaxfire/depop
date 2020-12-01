package com.jaxfire.depop.data.repository

import com.jaxfire.depop.data.network.response.NO_SIZE_SUPPLIED
import com.jaxfire.depop.data.network.response.ProductResponse
import com.jaxfire.depop.data.network.response.ProductResponse.ProductData.FormatDataContainer.FormatData
import com.jaxfire.depop.data.repository.entity.Product
import com.jaxfire.depop.data.repository.entity.Product.Picture
import com.jaxfire.depop.data.repository.entity.Product.Picture.*

class ProductMapper {

    fun mapToDomainProducts(dataProducts: List<ProductResponse.ProductData>): List<Product> {
        return dataProducts.map { rawProduct ->

            val pictures = mutableListOf<Picture>()

            if (rawProduct.picturesData != null) {

                rawProduct.picturesData.forEach { rawPictureData ->

                    if (rawPictureData.formats != null) {
                        val formatsList = mutableListOf<Pair<PictureSize, String>?>()
                        formatsList.add(generateFormatPair(rawPictureData.formats.p1))
                        formatsList.add(generateFormatPair(rawPictureData.formats.p2))
                        formatsList.add(generateFormatPair(rawPictureData.formats.p4))
                        formatsList.add(generateFormatPair(rawPictureData.formats.p5))
                        formatsList.add(generateFormatPair(rawPictureData.formats.p6))
                        formatsList.add(generateFormatPair(rawPictureData.formats.p7))
                        formatsList.add(generateFormatPair(rawPictureData.formats.p8))

                        val validFormats =
                            formatsList.filterNotNull().associateBy({ it.first }, { it.second })
                        if (validFormats.isNotEmpty()) {
                            pictures.add(Picture(validFormats))
                        }
                    }
                }
            }

            return@map Product(
                userId = rawProduct.id,
                description = rawProduct.description,
                pictures = pictures
            )
        }
    }

    private fun generateFormatPair(formatData: FormatData?): Pair<PictureSize, String>? {
        return if (
            formatData == null ||
            formatData.width == NO_SIZE_SUPPLIED ||
            formatData.height == NO_SIZE_SUPPLIED ||
            formatData.url.isEmpty()
        ) {
            null
        } else {
            Pair(getSize(formatData.width), formatData.url)
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
}