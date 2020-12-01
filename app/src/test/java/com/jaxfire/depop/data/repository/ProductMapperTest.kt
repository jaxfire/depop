package com.jaxfire.depop.data.repository

import com.google.common.truth.Truth.assertThat
import com.jaxfire.depop.application.di.modules.appModule
import com.jaxfire.depop.data.testUtil.buildProductData
import com.jaxfire.depop.data.testUtil.buildProductResponse
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class ProductMapperTest: KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(appModule)
    }

    private val productMapper : ProductMapper by inject()

    @Test
    fun `GIVEN A empty list of ProductData WHEN mapToDomainProducts is called THEN an empty list of Products is returned`() {
        assertThat(productMapper.mapToDomainProducts(buildProductResponse(numOfProducts = 0).productData!!)).isEmpty()
    }

    @Test
    fun `GIVEN A number of valid ProductData empty WHEN mapToDomainProducts is called THEN an equivalent number of Products are returned`() {
        assertThat(productMapper.mapToDomainProducts(buildProductResponse(numOfProducts = 10).productData!!)).hasSize(10)
    }

    @Test
    fun `GIVEN A ProductData is null WHEN mapToDomainProducts is called THEN the returned Product has a non-null pictures property`() {
        assertThat(productMapper.mapToDomainProducts(listOf(buildProductData())).first().pictures).isNotNull()
    }
    @Test
    fun `GIVEN A ProductData that is null WHEN mapToDomainProducts is called THEN the returned Product has an empty pictures property`() {
        assertThat(productMapper.mapToDomainProducts(listOf(buildProductData())).first().pictures).isEmpty()
    }
}