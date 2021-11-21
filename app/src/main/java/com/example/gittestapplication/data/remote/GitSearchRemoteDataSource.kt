package com.example.gittestapplication.data.remote

import com.example.gittestapplication.data.api.ApiServer
import com.example.gittestapplication.data.model.SearchRepositoryResponse
import com.example.gittestapplication.domain.Result
import com.example.gittestapplication.domain.model.Order
import com.example.gittestapplication.domain.model.Sort

class GitSearchRemoteDataSource(
    private val apiService: ApiServer.GitService
) {
    suspend fun fetchRepositories(query: String, sort: Sort, order: Order, pageSize: Int, pageNumber: Int): Result<SearchRepositoryResponse?> {
        return Result.create { apiService.searchRepositoriesResult(query, sort.queryName, order.queryName, pageSize, pageNumber) }
    }
}