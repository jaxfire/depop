package com.jaxfire.depop.data.network

import com.google.common.truth.Truth.assertThat
import com.jaxfire.depop.application.di.modules.appModule
import com.jaxfire.depop.data.testUtil.buildProductResponse
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given
import org.mockito.Mockito

class RemoteDataSourceImplTest : KoinTest {

    private val remoteDataSource : RemoteDataSource by inject()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(appModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Test
    fun `GIVEN The API response's json property 'objects' is null WHEN the popular products are requested THEN return an empty list of products`() {
        declareMock<ProductApiService> {
            runBlocking {
                given(getPopularProducts()).willReturn(buildProductResponse(numOfProducts = 0, productData = null))
            }
        }

        runBlocking {
            assertThat(remoteDataSource.getProducts()).isEmpty()
        }
    }

    @Test
    fun `GIVEN A valid API response WHEN the popular products are requested THEN return a correct list of products`() {
        declareMock<ProductApiService> {
            runBlocking {
                given(getPopularProducts()).willReturn(buildProductResponse(numOfProducts = 4))
            }
        }

        runBlocking {
            assertThat(remoteDataSource.getProducts()).isNotEmpty()
            assertThat(remoteDataSource.getProducts()).hasSize(4)
        }
    }
}