package com.jaxfire.depop.ui.fragments.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaxfire.depop.R
import com.jaxfire.depop.ui.fragments.sharedViewModel.ListDetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class DetailFragment : Fragment() {

    private val viewModel by sharedViewModel<ListDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onResume() {
        super.onResume()
        textViewDetailFragmentUserId.text = viewModel.selectedProduct?.userId.toString()
    }
}