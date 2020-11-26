package com.jaxfire.depop.application.di.modules

import com.jaxfire.depop.ui.fragments.sharedViewModel.ListDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // MyViewModel ViewModel
    viewModel { ListDetailViewModel() }
}