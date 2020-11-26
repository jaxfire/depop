package com.jaxfire.depop.data.repository.entity

data class Product(
    val userId: Long,
    val description: String,
    val pictures: List<Picture>
) {
    data class Picture(
        val size: Pair<Int, Int>,
        val url: String
    )
}
