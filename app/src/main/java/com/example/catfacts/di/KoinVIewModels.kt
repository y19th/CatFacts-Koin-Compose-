package com.example.catfacts.di

import com.example.catfacts.domain.viewmodels.FactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelsModule = module {

    viewModel {
        FactsViewModel()
    }
}