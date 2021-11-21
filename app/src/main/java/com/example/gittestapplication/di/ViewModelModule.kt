package com.example.gittestapplication.di

import com.example.gittestapplication.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModules = module {
    viewModel {
        MainViewModel()
    }
}