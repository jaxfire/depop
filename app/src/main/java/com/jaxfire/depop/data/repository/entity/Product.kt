package com.jaxfire.depop.data.repository.entity

import com.jaxfire.depop.data.repository.entity.Product.Picture.PictureSize.*

data class Product(
    val userId: Long,
    val description: String,
    val pictures: List<Picture>
) {
    class Picture(
        private val formats: Map<PictureSize, String>,
    ) {
        enum class PictureSize {
            XXS, XS, S, M, L, XL, XXL
        }

        fun getImageUrl(pictureSize: PictureSize): String {
            return when (pictureSize) {
                XXS -> {
                    formats.getOrElse(XXS) { getImageUrl(XS) }
                }
                XS -> {
                    formats.getOrElse(XS) { getImageUrl(S) }
                }
                S -> {
                    formats.getOrElse(S) { getImageUrl(M) }
                }
                M -> {
                    formats.getOrElse(M) { getImageUrl(L) }
                }
                L -> {
                    formats.getOrElse(L) { getImageUrl(XL) }
                }
                XL -> {
                    formats.getOrElse(XL) { getImageUrl(XXL) }
                }
                XXL -> {
                    formats.getOrElse(XXL) { "" }
                }
            }
        }
    }
}
