package com.jaxfire.depop.data.network.response

import com.google.gson.annotations.SerializedName

const val NO_SIZE_SUPPLIED = 0

data class ProductResponse(
    @SerializedName("objects")
    val productData: List<ProductData>?
) {

    data class ProductData(
        val id: Long = 0,
        val description: String = "This item does not have a description.",
        @SerializedName("pictures_data")
        val picturesData: List<PictureData>?
    ) {
        data class PictureData(
            val formats: FormatDataContainer?
        )

        data class FormatDataContainer(
            @SerializedName("P0")
            val p0: FormatData?,
            @SerializedName("P1")
            val p1: FormatData?,
            @SerializedName("P2")
            val p2: FormatData?,
            @SerializedName("P4")
            val p4: FormatData?,
            @SerializedName("P5")
            val p5: FormatData?,
            @SerializedName("P6")
            val p6: FormatData?,
            @SerializedName("P7")
            val p7: FormatData?,
            @SerializedName("P8")
            val p8: FormatData?,
        ) {
            data class FormatData(
                val url: String = "",
                val height: Int = NO_SIZE_SUPPLIED,
                val width: Int = NO_SIZE_SUPPLIED
            )
        }
    }
}