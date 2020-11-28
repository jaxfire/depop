package com.jaxfire.depop.ui

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jaxfire.depop.R
import com.jaxfire.depop.ui.fragments.sharedViewModel.ListDetailViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<ListDetailViewModel>()

    private val showProgressSpinnerObserver = Observer<Boolean> { initInProgress ->
        val visibility = if(initInProgress) VISIBLE else GONE
        viewMainActivityInitOverlay.visibility = visibility
        textViewMainActivityInitText.visibility = visibility
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.showProgressSpinner.observe(this, showProgressSpinnerObserver)
    }
}