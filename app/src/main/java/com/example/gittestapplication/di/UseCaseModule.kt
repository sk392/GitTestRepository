package com.example.gittestapplication.di

import com.example.gittestapplication.domain.usecase.GitSearchUseCase
import org.koin.dsl.module

val useCaseModules = module {
    factory {
        GitSearchUseCase(
            repository = get()
        )
    }
}