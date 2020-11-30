package com.jaxfire.depop.application.di.modules

import com.jaxfire.depop.data.network.interceptor.ConnectivityInterceptor
import com.jaxfire.depop.data.network.interceptor.ConnectivityInterceptorImpl
import com.jaxfire.depop.data.network.ProductApiService
import com.jaxfire.depop.data.network.RemoteDataSource
import com.jaxfire.depop.data.network.RemoteDataSourceImpl
import com.jaxfire.depop.data.repository.ProductMapper
import com.jaxfire.depop.data.repository.ProductRepositoryImpl
import com.jaxfire.depop.data.repository.ProductRepository
import com.jaxfire.depop.ui.fragments.sharedViewModel.ListDetailViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<ConnectivityInterceptor> { ConnectivityInterceptorImpl(androidApplication()) }
    single { ProductMapper() }
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    single { ProductApiService(get()) }
    single<ProductRepository> { ProductRepositoryImpl(get(), get()) }
    viewModel { ListDetailViewModel(androidApplication(), get()) }
}