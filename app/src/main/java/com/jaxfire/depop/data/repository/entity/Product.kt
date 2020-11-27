package com.jaxfire.depop.data.repository.entity

data class Product(
    val userId: Long,
    val fullDescription: String,
    val shortDescription: String,
    val pictures: List<Picture>
) {
    data class Picture(
        val formats: List<Format>,
    ) {
        class Format(
            val size: Pair<Int, Int>,
            val url: String
        )
    }
}
