package com.jaxfire.depop.ui.fragments.sharedViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jaxfire.depop.R
import com.jaxfire.depop.application.DepopApplication
import com.jaxfire.depop.data.repository.ProductRepository
import com.jaxfire.depop.data.repository.ResultWrapper
import com.jaxfire.depop.data.repository.entity.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListDetailViewModel(
    application: Application,
    private val productRepository: ProductRepository
) : AndroidViewModel(application) {

    val showProgressSpinner = MutableLiveData<Boolean>()
    val products = MutableLiveData<List<Product>>()
    val showErrorMessage = MutableLiveData<String>()

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        showProgressSpinner.postValue(true)

        viewModelScope.launch(Dispatchers.IO) {
            when (val response = productRepository.getAllProducts()) {
                is ResultWrapper.Success<List<Product>> -> handleSuccess(response.value)
                is ResultWrapper.NetworkError -> handleNetworkError()
                is ResultWrapper.GenericError -> handleGenericError()
            }
            showProgressSpinner.postValue(false)
        }
    }

    private fun handleSuccess(result: List<Product>) {
        products.postValue(result)
    }

    private fun handleNetworkError() {
        val errorMessage = getApplication<DepopApplication>().applicationContext.getString(R.string.network_error_message)
        showErrorMessage.postValue(errorMessage)
    }

    private fun handleGenericError() {
        val errorMessage = getApplication<DepopApplication>().applicationContext.getString(R.string.generic_error_message)
        showErrorMessage.postValue(errorMessage)
    }
}