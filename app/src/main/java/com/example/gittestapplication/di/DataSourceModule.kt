package com.example.gittestapplication.di

import com.example.gittestapplication.data.api.ApiServer
import com.example.gittestapplication.data.remote.GitSearchRemoteDataSource
import org.koin.dsl.module

val dataSourceModules = module {
    single {
        GitSearchRemoteDataSource(
            apiService = ApiServer.apiService()
        )
    }
}