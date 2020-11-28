package com.jaxfire.depop.ui.fragments.list

import android.os.Bundle
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
        containerListFragmentErrorOverlay.visibility = VISIBLE
        textViewListFragmentErrorMessage.text = errorMessage

    }

    private val productsObserver = Observer<List<Product>> { products ->
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
        recyclerViewListFragmentProduct.layoutManager = linearLayoutManager
        adapter = ProductAdapter(mutableListOf(), this)
        recyclerViewListFragmentProduct.adapter = adapter

        viewModel.showErrorMessage.observe(viewLifecycleOwner, errorObserver)
        viewModel.products.observe(viewLifecycleOwner, productsObserver)
    }

    override fun productSelectionHandler(product: Product) {
        viewModel.selectedProduct = product
        findNavController().navigate(R.id.action_listFragment_to_detailFragment)
    }
}