package com.jaxfire.depop.data.network.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("objects")
    val products: List<Product>
) {

    data class Product(
        val id: Long,
        val description: String,
        @SerializedName("pictures_data")
        val picturesData: List<PictureData>
    ) {
        data class PictureData(
            val formats: FormatData
        )

        data class FormatData(
            @SerializedName("P0")
            val p0: FormatData,
            @SerializedName("P1")
            val p1: FormatData,
            @SerializedName("P2")
            val p2: FormatData,
            @SerializedName("P3")
            val p3: FormatData,
            @SerializedName("P4")
            val p4: FormatData,
            @SerializedName("P5")
            val p5: FormatData,
            @SerializedName("P6")
            val p6: FormatData,
            @SerializedName("P7")
            val p7: FormatData,
            @SerializedName("P8")
            val p8: FormatData,
            @SerializedName("P9")
            val p9: FormatData,
        ) {
            data class FormatData(
                val url: String,
                val height: Int,
                val width: Int
            )
        }
    }
}