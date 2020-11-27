package com.jaxfire.depop.ui.fragments.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaxfire.depop.R
import com.jaxfire.depop.data.repository.entity.Product
import com.jaxfire.depop.ui.fragments.list.adapter.ProductAdapter
import com.jaxfire.depop.ui.fragments.sharedViewModel.ListDetailViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ListFragment : Fragment(), ProductAdapter.ProductItemClickListener {

    private val viewModel by sharedViewModel<ListDetailViewModel>()

    private lateinit var adapter: ProductAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val errorObserver = Observer<String> { errorMessage ->
        errorOverlay.visibility = VISIBLE
        errorTextView.text = errorMessage
    }

    private val showProgressSpinnerObserver = Observer<Boolean> { requestInProgress ->
        progressSpinner.visibility = if (requestInProgress) VISIBLE else GONE
    }

    private val productsObserver = Observer<List<Product>> { products ->
        Log.d("jim", "Product list updated. Size: ${products.size}")
        adapter.setProducts(products)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearLayoutManager = LinearLayoutManager(requireContext())
        productRecyclerView.layoutManager = linearLayoutManager
        adapter = ProductAdapter(mutableListOf(), this)
        productRecyclerView.adapter = adapter

        viewModel.showErrorMessage.observe(viewLifecycleOwner, errorObserver)
        viewModel.showProgressSpinner.observe(viewLifecycleOwner, showProgressSpinnerObserver)
        viewModel.products.observe(viewLifecycleOwner, productsObserver)
    }

    override fun productSelectionHandler(product: Product) {
        viewModel.selectedProduct = product
        findNavController().navigate(R.id.action_listFragment_to_detailFragment)
    }
}