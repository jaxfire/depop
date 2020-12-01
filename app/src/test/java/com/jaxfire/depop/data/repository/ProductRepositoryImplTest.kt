package com.jaxfire.depop.data.repository

import com.google.common.truth.Truth.assertThat
import com.jaxfire.depop.application.di.modules.appModule
import com.jaxfire.depop.data.network.RemoteDataSource
import com.jaxfire.depop.data.network.interceptor.ConnectivityInterceptor
import com.jaxfire.depop.data.testUtil.buildProductResponse
import com.jaxfire.depop.internal.NoConnectivityException
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import retrofit2.HttpException

class ProductRepositoryImplTest : KoinTest {

    private val productRepository: ProductRepository by inject()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(appModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Test
    fun `GIVEN RemoteDataSource returns a valid response WHEN the popular products are requested THEN a ResultWrapper of type Success is returned`() {
        declareMock<RemoteDataSource> {
            runBlocking {
                given(getPopularProducts()).willReturn(buildProductResponse().productData)
                assertThat(productRepository.getPopularProducts()).isInstanceOf(ResultWrapper.Success::class.java)
            }
        }
    }

    @Test
    fun `GIVEN OkHttp networkInterceptor throws a NoConnectivityException WHEN the popular products are requested THEN a ResultWrapper of type NetworkConnectivityError is returned`() {
        declareMock<ConnectivityInterceptor> {
            given(intercept(BDDMockito.any())).willThrow(
                NoConnectivityException()
            )
        }
        runBlocking {
            assertThat(productRepository.getPopularProducts()).isSameInstanceAs(ResultWrapper.NetworkConnectivityError)
        }
    }

    @Test
    fun `GIVEN RemoteDataSource throws a HttpException WHEN the popular products are requested THEN a ResultWrapper of type GenericError is returned`() {
        declareMock<RemoteDataSource> {
            runBlocking {
                given(getPopularProducts()).willThrow(HttpException::class.java)
                assertThat(productRepository.getPopularProducts()).isInstanceOf(ResultWrapper.GenericError::class.java)
            }
        }
    }

    @Test
    fun `GIVEN RemoteDataSource throws a RuntimeException WHEN the popular products are requested THEN a ResultWrapper of type GenericError is returned`() {
        declareMock<RemoteDataSource> {
            runBlocking {
                given(getPopularProducts()).willThrow(RuntimeException::class.java)
                assertThat(productRepository.getPopularProducts()).isInstanceOf(ResultWrapper.GenericError::class.java)
            }
        }
    }
}