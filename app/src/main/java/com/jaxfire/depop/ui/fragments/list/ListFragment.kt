package com.jaxfire.depop.ui.fragments.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaxfire.depop.R
import com.jaxfire.depop.ui.fragments.sharedViewModel.ListDetailViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ListFragment : Fragment() {

    private val viewModel by sharedViewModel<ListDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        Log.d("jim", "Detail: ${viewModel.myValue}")
    }
}