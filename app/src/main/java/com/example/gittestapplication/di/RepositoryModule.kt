package com.example.gittestapplication.di

import com.example.gittestapplication.data.repository.GitSearchRepositoryImpl
import com.example.gittestapplication.domain.repository.GitSearchRepository
import org.koin.dsl.module

val repositoryModules = module {
    single<GitSearchRepository> {
        GitSearchRepositoryImpl(
            dataSource = get()
        )
    }
}