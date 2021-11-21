package com.example.gittestapplication.data.repository

import com.example.gittestapplication.data.model.RepositoryResponse
import com.example.gittestapplication.data.model.SearchRepositoryResponse
import com.example.gittestapplication.data.model.toEntity
import com.example.gittestapplication.data.remote.GitSearchRemoteDataSource
import com.example.gittestapplication.domain.Result
import com.example.gittestapplication.domain.model.Order
import com.example.gittestapplication.domain.model.Repository
import com.example.gittestapplication.domain.model.Sort
import com.example.gittestapplication.domain.repository.GitSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class GitSearchRepositoryImpl(
    private val dataSource: GitSearchRemoteDataSource
) : GitSearchRepository {
    override suspend fun fetchRepositories(query: String, sort: Sort, order: Order, pageSize: Int, pageNumber: Int): Result<List<Repository>> =
        withContext(Dispatchers.IO) {
            when (val result: Result<SearchRepositoryResponse?> = dataSource.fetchRepositories(query, sort, order, pageSize, pageNumber)) {
                is Result.Success -> {
                    Result.create {
                        val repositories: List<RepositoryResponse>? = result.value?.items?.filterNotNull()
                        val resultList = repositories?.mapNotNull {
                            it.toEntity()
                        }
                        if (!resultList.isNullOrEmpty()) {
                            resultList
                        } else {
                            throw Exception("custom Exception")
                        }
                    }

                }
                is Result.Failure -> {
                    Result.Failure(result.throwable)
                }
            }
        }
}