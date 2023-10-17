package com.aravind.kmmassignment.android

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UiMainViewModel(get(), get(), get()) }
}