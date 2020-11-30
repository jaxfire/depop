package com.jaxfire.depop.data.testUtil

import android.os.Process
import com.jaxfire.depop.data.network.response.ProductResponse
import com.jaxfire.depop.data.network.response.ProductResponse.ProductData
import com.jaxfire.depop.data.network.response.ProductResponse.ProductData.FormatDataContainer
import com.jaxfire.depop.data.network.response.ProductResponse.ProductData.FormatDataContainer.FormatData
import com.jaxfire.depop.data.network.response.ProductResponse.ProductData.PictureData

fun buildFormatData(
    url: String = "https://d2h1pu99sxkfvn.cloudfront.net/b0/1717225/330365435_aq5hSoyELs/U2.jpg",
    height: Int = 640,
    width: Int = 640
): FormatData {
    return FormatData(
        url, height, width
    )
}

fun buildFormatDataContainer(
    p0: FormatData? = buildFormatData(height = 1280, width = 1280),
    p1: FormatData? = buildFormatData(height = 640, width = 640),
    p2: FormatData? = buildFormatData(height = 150, width = 150),
    p4: FormatData? = buildFormatData(height = 210, width = 210),
    p5: FormatData? = buildFormatData(height = 320, width = 320),
    p6: FormatData? = buildFormatData(height = 480, width = 480),
    p7: FormatData? = buildFormatData(height = 960, width = 960),
    p8: FormatData? = buildFormatData(height = 1280, width = 1280),
): FormatDataContainer {
    return FormatDataContainer(p0, p1, p2, p4, p5, p6, p7, p8)
}

fun buildPictureData(
    formats: FormatDataContainer? = buildFormatDataContainer()
): PictureData {
    return PictureData(formats)
}

fun buildProductData(
    id: Long = 1,
    description: String = """
        5 lines example description,
        5 lines example description,
        5 lines example description,
        5 lines example description,
        5 lines example description,
        """.trimMargin(),
    numOfPictures: Int = 1,
    picturesData: List<PictureData>? = null
): ProductData {
    val pictures = mutableListOf<PictureData>()
    repeat(numOfPictures) {
        pictures.add(buildPictureData())
    }
    return ProductData(id, description, picturesData ?: pictures)
}

fun buildProductResponse(
    numOfProducts: Int = 1,
    productData: List<ProductData>? = null
): ProductResponse {
    val products = mutableListOf<ProductData>()
    repeat(numOfProducts) {
        products.add(buildProductData())
    }
    return ProductResponse(productData ?: products)
}