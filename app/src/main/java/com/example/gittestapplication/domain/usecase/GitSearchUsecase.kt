package com.example.gittestapplication.domain.usecase

import com.example.gittestapplication.domain.Result
import com.example.gittestapplication.domain.model.Order
import com.example.gittestapplication.domain.model.Repository
import com.example.gittestapplication.domain.model.Sort
import com.example.gittestapplication.domain.repository.GitSearchRepository

class GitSearchUseCase(
    private val repository: GitSearchRepository
) {
    suspend operator fun invoke(
        query: String,
        sort: Sort,
        order: Order,
        pageSize: Int,
        pageNumber: Int
    ): Result<List<Repository>> {
        return repository.fetchRepositories(query, sort, order, pageSize, pageNumber)
    }
}