package com.jaxfire.depop.ui.fragments.sharedViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaxfire.depop.data.repository.entity.Product
import com.jaxfire.depop.data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListDetailViewModel(productRepository: ProductRepository): ViewModel() {

    val showProgressSpinner = MutableLiveData<Boolean>()
    val products = MutableLiveData<List<Product>>()

    init {
        showProgressSpinner.postValue(true)

        viewModelScope.launch(Dispatchers.IO) {
            val response = productRepository.getAllProducts()
            // TODO: Add response error handling
            products.postValue(response)

            showProgressSpinner.postValue(false)
        }
    }
}