package com.jaxfire.depop.ui.fragments.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.jaxfire.depop.R
import com.jaxfire.depop.data.repository.entity.Product
import com.jaxfire.depop.ui.fragments.sharedViewModel.ListDetailViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ListFragment : Fragment() {

    private val viewModel by sharedViewModel<ListDetailViewModel>()

    private val errorObserver = Observer<String> { errorMessage ->
        errorOverlay.visibility = VISIBLE
        errorTextView.text = errorMessage
    }

    private val showProgressSpinnerObserver = Observer<Boolean> { requestInProgress ->
        progressSpinner.visibility = if (requestInProgress) VISIBLE else GONE
    }

    private val productsObserver = Observer<List<Product>> { products ->
        Log.d("jim", "Product list updated. Size: ${products.size}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.showErrorMessage.observe(this, errorObserver)
        viewModel.showProgressSpinner.observe(this, showProgressSpinnerObserver)
        viewModel.products.observe(this, productsObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onResume() {
        super.onResume()

//        testButton.setOnClickListener {
//            findNavController().navigate(R.id.action_listFragment_to_detailFragment)
//        }
    }
}