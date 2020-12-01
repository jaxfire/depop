package com.jaxfire.depop.data.network

import com.jaxfire.depop.data.network.interceptor.ConnectivityInterceptor
import com.jaxfire.depop.data.network.response.ProductResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ProductApiService {

    @GET("products/popular/?offset_id=")
    suspend fun getPopularProducts(): ProductResponse

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ProductApiService {

            val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.garage.me/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProductApiService::class.java)
        }
    }
}
